package com.hana.onemoim.community.service;

import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.account.mapper.AccountMapper;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final GatheringMemberMapper gatheringMemberMapper;
    private final GatheringMapper gatheringMapper;
    private final CalendarMapper calendarMapper;
    private final GalleryPostMapper galleryPostMapper;
    private final ImageMapper imageMapper;
    private final GalleryCommentMapper galleryCommentMapper;
    private final MemberMapper memberMapper;
    private final InterestMapper interestMapper;
    private final AccountMapper accountMapper;
    private final GatheringTransactionMapper gatheringTransactionMapper;
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
}
