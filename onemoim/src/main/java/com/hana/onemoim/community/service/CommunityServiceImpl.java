package com.hana.onemoim.community.service;

import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.account.dto.AccountTransferDto;
import com.hana.onemoim.account.dto.MemberTransactionDto;
import com.hana.onemoim.account.mapper.AccountMapper;
import com.hana.onemoim.account.mapper.TransactionMapper;
import com.hana.onemoim.common.dto.ImageDto;
import com.hana.onemoim.common.mapper.ImageMapper;
import com.hana.onemoim.common.mapper.InterestMapper;
import com.hana.onemoim.community.dto.*;
import com.hana.onemoim.community.mapper.*;
import com.hana.onemoim.gathering.dto.GatheringDto;
import com.hana.onemoim.gathering.mapper.GatheringMapper;
import com.hana.onemoim.member.mapper.MemberMapper;
import com.hana.onemoim.util.S3uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter SECOND_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");
    private static final int TRANSACTION_TYPE_WITHDRAW = 51;  // 출금 거래 코드
    private static final int TRANSACTION_TYPE_DEPOSIT = 50;   // 입금 거래 코드
    private static final int PAYMENT_CYCLE_YEARLY = 60;   // 납부 주기 코드 - 연
    private static final int PAYMENT_CYCLE_QUARTERLY = 61;   // 납부 주기 코드 - 분기
    private static final int PAYMENT_CYCLE_MONTHLY = 62;   // 납부 주기 코드 - 월
    private final GatheringMemberMapper gatheringMemberMapper;
    private final GatheringMapper gatheringMapper;
    private final CalendarMapper calendarMapper;
    private final GalleryPostMapper galleryPostMapper;
    private final ImageMapper imageMapper;
    private final GalleryCommentMapper galleryCommentMapper;
    private final MemberMapper memberMapper;
    private final InterestMapper interestMapper;
    private final AccountMapper accountMapper;
    private final PaymentMapper paymentMapper;
    private final GatheringTransactionMapper gatheringTransactionMapper;
    private final TransactionMapper transactionMapper;
    private final S3uploader s3uploader;

    // 모임원 찾기
    @Override
    public List<GatheringMemberDto> findGatheringMemberByGatheringId(int gatheringId) {
        return gatheringMemberMapper.selectGatheringMemberByGatheringId(gatheringId);
    }

    // 모임원 상태 코드 확인
    @Override
    public int checkMemberStatusCode(int gatheringId, int memberId) {
        return gatheringMemberMapper.isMemberStatusCodeActive(gatheringId, memberId);
    }

    // 커뮤니티 정보 조회(메인페이지 접근용)
    @Override
    public CommunityMainDto searchCommunityInfo(int gatheringId, int memberId) {
        int memberStatusCode = checkMemberStatusCode(gatheringId, memberId);

        if (memberStatusCode == 71) { // 활동 정지 상태의 모임원이 모임에 접근할 경우
            return CommunityMainDto.builder()
                    .gatheringDto(null)
                    .message("활동이 정지된 모임원입니다. \n모임장에게 문의하세요")
                    .redirectRequired(true)
                    .build();
        } else if (memberStatusCode == 72) { // 승인 대기 상태의 모임원이 모임에 접근할 경우
            return CommunityMainDto.builder()
                    .gatheringDto(null)
                    .message("승인 대기 상태의 모임원입니다.")
                    .redirectRequired(true)
                    .build();
        }

        GatheringDto gatheringDto = gatheringMapper.selectGatheringByGatheringId(false, gatheringId);
        gatheringDto.setGatheringCoverImageUrl(gatheringMapper.selectGatheringCoverImage(gatheringId));
        gatheringDto.setGatheringLeaderName(memberMapper.selectNameByLeaderId(gatheringDto.getGatheringLeaderId()));
        gatheringDto.setGatheringMemberNumber(gatheringMemberMapper.countGatheringMemberByGatheringId(gatheringId));
        return CommunityMainDto.builder()
                .gatheringDto(gatheringDto)
                .message(null)
                .redirectRequired(false)
                .build();
    }

    // 개별 커뮤니티 정보 조회
    @Override
    public CommunityInfoDto getCommunityInfo(int gatheringId) {
        return CommunityInfoDto.builder()
                .gatheringDto(gatheringMapper.selectGatheringByGatheringId(false, gatheringId))
                .gatheringLeaderId(gatheringMapper.selectGatheringLeaderId(gatheringId))
                .gatheringMemberDtoList(findGatheringMemberByGatheringId(gatheringId))
                .build();
    }

    // 모임원 상태 코드 변경
    @Override
    public void updateMemberStatusCode(int memberStatusCode, int memberId, int gatheringId) {
        gatheringMemberMapper.updateMemberStatusCode(memberStatusCode, memberId, gatheringId);
    }

    // 캘리더 일정 삽입
    @Override
    public void insertCalendarEvent(int gatheringId, CalendarEventDto calendarEventDto) {
        try {
            // eventStartDate와 eventEndDate를 Java의 Date 타입으로 변환
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date startDateTime = inputFormat.parse(calendarEventDto.getEventStartDate());
            Date endDateTime = inputFormat.parse(calendarEventDto.getEventEndDate());

            // 변환된 Date 객체를 Oracle 데이터베이스 형식의 문자열로 재변환
            SimpleDateFormat oracleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            calendarEventDto.setEventStartDate(oracleFormat.format(startDateTime));
            calendarEventDto.setEventEndDate(oracleFormat.format(endDateTime));

            // DB에 저장
            calendarMapper.insertCalendarEvent(calendarEventDto);
        } catch (Exception e) {
            throw new RuntimeException("일정을 추가하는 도중 오류가 발생했습니다.", e);
        }
    }

    // 캘린더 일정 조회
    @Override
    public List<CalendarEventDto> getAllCalendarEvent(int gatheringId) {
        return calendarMapper.selectCalendarEventByGatheringId(gatheringId);
    }

    // 일정 삭제
    @Override
    public void deleteCalendarEvent(int eventId) {
        calendarMapper.deleteCalendarEventByEventId(eventId);
    }

    // 일정 수정

    @Override
    public void updateCalendarEvent(CalendarEventDto calendarEventDto) {
        try {
            // eventStartDate와 eventEndDate를 Java의 Date 타입으로 변환
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date startDateTime = inputFormat.parse(calendarEventDto.getEventStartDate());
            Date endDateTime = inputFormat.parse(calendarEventDto.getEventEndDate());

            // 변환된 Date 객체를 Oracle 데이터베이스 형식의 문자열로 재변환
            SimpleDateFormat oracleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            calendarEventDto.setEventStartDate(oracleFormat.format(startDateTime));
            calendarEventDto.setEventEndDate(oracleFormat.format(endDateTime));

            // DB에 저장
            calendarMapper.updateCalendarEvent(calendarEventDto);
        } catch (Exception e) {
            throw new RuntimeException("일정을 추가하는 도중 오류가 발생했습니다.", e);
        }
    }

    // 갤러리 게시글 등록
    @Transactional
    @Override
    public void insertGalleryPost(int gatheringId, int memberId, GalleryPostDto galleryPostDto, List<MultipartFile> multipartFiles) {
        galleryPostDto.setPostId(galleryPostMapper.getNextPostSeq() + 1);
        galleryPostDto.setGatheringId(gatheringId);
        galleryPostDto.setGatheringMemberId(memberId);
        galleryPostMapper.insertGalleryPost(galleryPostDto); // 게시글 등록

        // 게시글 이미지 삽입
        if (multipartFiles != null) {
            for (MultipartFile multipartFile : multipartFiles) {
                try {
                    String url = s3uploader.Uploader(multipartFile);
                    ImageDto imageDto = ImageDto.builder()
                            .targetId(galleryPostDto.getPostId())
                            .imageUrl(url)
                            .build();
                    imageMapper.insertGalleryImage(imageDto);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    System.out.println("이미지 업로드 중 오류가 발생했습니다.");
                }
            }
        }
    }

    // 이미지 조회
    @Override
    public List<GalleryImageResponseDto> getAllImage(int gatheringId) {
        List<Integer> postIdList = galleryPostMapper.selectPostId(gatheringId);
        List<GalleryImageResponseDto> galleryImageResponseDtoList = new ArrayList<>();
        for (int postId : postIdList) {
            String imgUrl = imageMapper.selectImgUrlByPostId(postId);
            int writer = galleryPostMapper.selectWriter(postId);
            if (imgUrl != null) {
                galleryImageResponseDtoList.add(GalleryImageResponseDto.builder()
                        .postId(postId)
                        .imageUrl(imgUrl)
                        .gatheringMemberId(writer)
                        .build());
            }
        }
        return galleryImageResponseDtoList;
    }

    // 게시글 상세 조회
    @Override
    public GalleryPostResponseDto getPost(int postId) {
        galleryPostMapper.updateViewCnt(postId);
        GalleryPostDto galleryPostDto = galleryPostMapper.selectGalleryPost(postId);
        List<GalleryCommentDto> galleryCommentDtoList = galleryCommentMapper.selectGalleryCommentByPostId(postId);
        for (GalleryCommentDto galleryCommentDto : galleryCommentDtoList) {
            galleryCommentDto.setCreatedAt(calculateTimeAgo(galleryCommentDto.getCreatedAt()));
            galleryCommentDto.setMemberName(gatheringMemberMapper.selectGatheringMemberName(galleryCommentDto.getGatheringMemberId()));
        }

        return GalleryPostResponseDto.builder()
                .postId(postId)
                .gatheringId(galleryPostDto.getGatheringId())
                .gatheringMemberId(galleryPostDto.getGatheringMemberId())
                .gatheringMemberName(gatheringMemberMapper.selectGatheringMemberName(galleryPostDto.getGatheringMemberId()))
                .title(galleryPostDto.getTitle())
                .content(galleryPostDto.getContent())
                .viewCnt(galleryPostDto.getViewCnt())
                .commentCnt(galleryCommentDtoList.size())
                .galleryCommentDtoList(galleryCommentDtoList)
                .createdAt(calculateTimeAgo(galleryPostDto.getCreatedAt()))
                .modifiedAt(galleryPostDto.getModifiedAt())
                .imageUrlList(imageMapper.selectImgUrlForDetail(postId))
                .build();
    }

    // 게시글 작성일 수정
    public String calculateTimeAgo(String pastDateTimeString) {
        // 문자열을 LocalDateTime으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime past = LocalDateTime.parse(pastDateTimeString, formatter);

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(past, now);

        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String ago = "방금 전";

        if (absSeconds >= 60) {
            long minutes = absSeconds / 60;
            if (minutes < 60) {
                ago = minutes + "분 전";
            } else {
                long hours = minutes / 60;
                if (hours < 24) {
                    ago = hours + "시간 전";
                } else {
                    long days = hours / 24;
                    if (days < 7) {
                        ago = days + "일 전";
                    } else if (days < 30) {
                        long weeks = days / 7;
                        ago = weeks + "주 전";
                    } else {
                        long months = days / 30;
                        if (months < 12) {
                            ago = months + "달 전";
                        } else {
                            long years = months / 12;
                            ago = years + "년 전";
                        }
                    }
                }
            }
        }

        return ago;
    }

    // 댓글 작성
    @Override
    public String insertGalleryComment(int postId, int gatheringId, int memberId, String content) {
        galleryCommentMapper.insertGalleryComment(GalleryCommentDto.builder()
                .postId(postId)
                .gatheringMemberId(gatheringMemberMapper.selectGatheringMemberId(memberId, gatheringId))
                .gatheringId(gatheringId)
                .content(content)
                .build());
        return gatheringMemberMapper.selectGatheringMemberName(gatheringMemberMapper.selectGatheringMemberId(memberId, gatheringId));
    }

    // gathering_member_id 조회
    @Override
    public int getGatheringMemberId(int memberId, int gatheringId) {
        return gatheringMemberMapper.selectGatheringMemberId(memberId, gatheringId);
    }

    // 모임 관심사 조회
    @Override
    public List<String> getGatheringInterest(int gatheringId) {
        return interestMapper.selectInterestNameByGatheringId(gatheringId);
    }

    // 공지사항 조회
    @Override
    public List<GalleryPostDto> getNotice(int gatheringId) {
        return galleryPostMapper.selectNoticeTitle(gatheringId);
    }

    // 모임계좌번호 조회
    @Override
    public String getGatheringAccountNumber(int gatheringId) {
        return accountMapper.selectAccountNumberByGatheringId(gatheringId);
    }

    // 모임계좌거래내역 조회
    @Override
    public List<GatheringTransactionDto> getGatheringTransaction(String accountNumber) {
        return gatheringTransactionMapper.selectTransactionByAccountNumber(accountNumber);
    }

    // 사용자 전체 계좌 조회
    @Override
    public List<AccountDto> getAllAccountByPersonalIdNumber(String personaIdNumber) {
        return accountMapper.selectAccountByPersonalIdNumber(personaIdNumber);
    }

    // 회비확인
    @Override
    public int getGatheringPaymentAmount(int gatheringId) {
        return paymentMapper.selectPaymentAmount(gatheringId);
    }

    // 회비납입
    @Transactional
    @Override
    public void paymentTransfer(AccountTransferDto accountTransferDto, int gatheringId, int memberId) {
        int gatheringMemberId = gatheringMemberMapper.selectGatheringMemberId(memberId, gatheringId);
        processWithdrawal(accountTransferDto, gatheringId, gatheringMemberId);  // 출금 처리
        processDeposit(accountTransferDto, gatheringId, gatheringMemberId);     // 입금 처리
    }

    // 출금 프로세스
    private void processWithdrawal(AccountTransferDto accountTransferDto, int gatheringId, int gatheringMemberId) {
        accountMapper.updateAccountBalance(accountTransferDto); // 출금을 위한 잔액 업데이트
        int balanceAfterTransaction = accountMapper.selectBalance(accountTransferDto);
        createTransaction(accountTransferDto, TRANSACTION_TYPE_WITHDRAW, balanceAfterTransaction, gatheringId, gatheringMemberId);  // 출금 거래 기록 생성
    }

    // 입금(회비납입) 프로세스
    private void processDeposit(AccountTransferDto accountTransferDto, int gatheringId, int gatheringMemberId) {
        accountMapper.updateGatheringAccountBalanceDeposit(accountTransferDto); // 입금을 위한 잔액 업데이트
        AccountTransferDto depositDto = new AccountTransferDto();
        depositDto.setAccountNumber(accountTransferDto.getOtherAccountNumber());
        int balanceAfterDeposit = accountMapper.selectGatheringAccountBalance(depositDto);
        createTransaction(accountTransferDto, TRANSACTION_TYPE_DEPOSIT, balanceAfterDeposit, gatheringId, gatheringMemberId); // 입금 거래 기록 생성
    }

    // 거래내역 생성 및 모임회비 납부기록 생성
    private void createTransaction(AccountTransferDto accountTransferDto, int transactionType, int balanceAfterTransaction
            , int gatheringId, int gatheringMemberId) {
        if (transactionType == TRANSACTION_TYPE_DEPOSIT) { // 입금계좌(모임계좌) 기준
            gatheringTransactionMapper.insertGatheringTransaction(
                    MemberTransactionDto.builder()
                            .accountNumber(accountTransferDto.getOtherAccountNumber())
                            .otherAccountNumber(accountTransferDto.getAccountNumber())
                            .transactionTypeCode(transactionType)
                            .transactionAmount(accountTransferDto.getAmount())
                            .balanceAfterTransaction(balanceAfterTransaction)
                            .memo(accountTransferDto.getMemo())
                            .build());
        } else { // 출금계좌 기준
            transactionMapper.insertTransaction(MemberTransactionDto.builder()
                    .accountNumber(accountTransferDto.getAccountNumber())
                    .otherAccountNumber(accountTransferDto.getOtherAccountNumber())
                    .transactionTypeCode(transactionType)
                    .transactionAmount(accountTransferDto.getAmount())
                    .balanceAfterTransaction(balanceAfterTransaction)
                    .memo(accountTransferDto.getMemo())
                    .build());

            createPaymentRecord(gatheringId, gatheringMemberId);
        }
    }

    public void createPaymentRecord(int gatheringId, int gatheringMemberId) {
        // 1. 모임회비규칙 조회
        GatheringPaymentRuleDto rule = paymentMapper.selectPaymentRule(gatheringId);
        LocalDateTime startDate = LocalDateTime.parse(rule.getStartDate(), FORMATTER);

        // 2. 회비납부기한 및 회차 계산
        LocalDate paymentDueDate = calculatePaymentDueDate(startDate.toLocalDate(), rule.getPaymentCycleCode(), rule.getPaymentDay());
        int round = calculateTermNumber(startDate.toLocalDate(), rule.getPaymentCycleCode());

        paymentMapper.insertGatheringPaymentRecord(GatheringPaymentRecordDto.builder()
                .gatheringId(gatheringId)
                .gatheringMemberId(gatheringMemberId)
                .gatheringPaymentRuleId(rule.getGatheringPaymentRuleId())
                .paymentAmount(rule.getPaymentAmount())
                .paymentDueDate(paymentDueDate.format(SECOND_FORMATTER))
                .round(round)
                .build());
    }

    private LocalDate calculatePaymentDueDate(LocalDate startDate, int paymentCycleCode, int paymentDay) {
        switch (paymentCycleCode) {
            case PAYMENT_CYCLE_YEARLY: // 연
                return startDate.plusYears(1).withDayOfMonth(paymentDay);
            case PAYMENT_CYCLE_QUARTERLY: // 분기
                return startDate.plusMonths(3).withDayOfMonth(paymentDay);
            case PAYMENT_CYCLE_MONTHLY: // 월
                return startDate.plusMonths(1).withDayOfMonth(paymentDay);
            default:
                throw new IllegalArgumentException("Invalid paymentCycleCode: " + paymentCycleCode);
        }
    }

    private int calculateTermNumber(LocalDate startDate, int paymentCycleCode) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, LocalDate.now());
        switch (paymentCycleCode) {
            case PAYMENT_CYCLE_YEARLY: // 연
                return (int) (daysBetween / 365) + 1;
            case PAYMENT_CYCLE_QUARTERLY: // 분기
                return (int) (daysBetween / 90) + 1;
            case PAYMENT_CYCLE_MONTHLY: // 월
                return (int) (daysBetween / 30) + 1;
            default:
                throw new IllegalArgumentException("Invalid paymentCycleCode: " + paymentCycleCode);
        }
    }

    // 모임회비 납부 여부 확인
    @Override
    public boolean isPaymentMade(int gatheringId, int memberId) {
        int gatheringMemberId = gatheringMemberMapper.selectGatheringMemberId(memberId, gatheringId);

        // 모임회비규칙 조회
        GatheringPaymentRuleDto rule = paymentMapper.selectPaymentRule(gatheringId);
        LocalDateTime startDate = LocalDateTime.parse(rule.getStartDate(), FORMATTER);

        // 회비납부기한 계산
        LocalDate paymentDueDate = calculatePaymentDueDate(startDate.toLocalDate(), rule.getPaymentCycleCode(), rule.getPaymentDay());

        Map<String, Object> params = new HashMap<>();
        params.put("gatheringId", gatheringId);
        params.put("gatheringMemberId", gatheringMemberId);
        params.put("paymentDueDate", paymentDueDate.format(SECOND_FORMATTER));

        int count = paymentMapper.selectPaymentRecord(params);
        return count > 0;
    }

    // 모임계좌 잔액 조회
    @Override
    public int getGatheringBalance(int gatheringId) {
        return accountMapper.selectGatheringBalance(gatheringId);
    }

    // 모임계좌 출금(계좌이체)
    @Transactional
    @Override
    public void gatheringTransfer(AccountTransferDto accountTransferDto) {
        processWithdrawalForGatheringTransfer(accountTransferDto);  // 출금 처리
        processDepositForGatheringTransfer(accountTransferDto);     // 입금 처리
    }

    // 출금 프로세스
    private void processWithdrawalForGatheringTransfer(AccountTransferDto accountTransferDto) {
        System.out.println(accountTransferDto.getAccountNumber());
        accountMapper.updateGatheringAccountBalance(accountTransferDto); // 출금을 위한 잔액 업데이트
        int balanceAfterDeposit = accountMapper.selectGatheringAccountBalance(accountTransferDto);
        createTransactionForWithdrawal(accountTransferDto, TRANSACTION_TYPE_WITHDRAW, balanceAfterDeposit);  // 출금 거래 기록 생성
    }

    // 입금(회비납입) 프로세스
    private void processDepositForGatheringTransfer(AccountTransferDto accountTransferDto) {
        accountMapper.updateAccountBalanceDeposit(accountTransferDto); // 입금을 위한 잔액 업데이트
        int balanceAfterDeposit = accountMapper.selectBalanceForGatheringAccountWithdrawal(accountTransferDto);
        createTransactionForWithdrawal(accountTransferDto, TRANSACTION_TYPE_DEPOSIT, balanceAfterDeposit); // 입금 거래 기록 생성
    }

    // 거래내역 생성
    private void createTransactionForWithdrawal(AccountTransferDto accountTransferDto, int transactionType, int balanceAfterTransaction) {
        if (transactionType == TRANSACTION_TYPE_DEPOSIT) { // 입금계좌(개인계좌) 기준
           transactionMapper.insertTransaction(
                    MemberTransactionDto.builder()
                            .accountNumber(accountTransferDto.getOtherAccountNumber())
                            .otherAccountNumber(accountTransferDto.getAccountNumber())
                            .transactionTypeCode(transactionType)
                            .transactionAmount(accountTransferDto.getAmount())
                            .balanceAfterTransaction(balanceAfterTransaction)
                            .memo(accountTransferDto.getMemo())
                            .build());
        } else { // 출금계좌(모임계좌) 기준
            gatheringTransactionMapper.insertGatheringTransaction(MemberTransactionDto.builder()
                    .accountNumber(accountTransferDto.getAccountNumber())
                    .otherAccountNumber(accountTransferDto.getOtherAccountNumber())
                    .transactionTypeCode(transactionType)
                    .transactionAmount(accountTransferDto.getAmount())
                    .balanceAfterTransaction(balanceAfterTransaction)
                    .memo(accountTransferDto.getMemo())
                    .build());
        }
    }
}
