package com.hana.onemoim.community.service;

import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.account.dto.AccountTransferDto;
import com.hana.onemoim.community.dto.*;
import com.hana.onemoim.gathering.dto.CardBenefitDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommunityService {

    // 모임원 조회
    List<GatheringMemberDto> findGatheringMemberByGatheringId(int gatheringId);

    // 모임원 상태 코드 확인
    int checkMemberStatusCode(int gatheringId, int memberId);

    // 모임 메인 페이지 조회
    CommunityMainDto searchCommunityInfo(int gatheringId, int memberId);

    // 커뮤니티 - 모임 정보 조회
    CommunityInfoDto getCommunityInfo(int gatheringId);

    // 모임원 상태 코드 변경
    void updateMemberStatusCode(int memberStatusCode, int memberId, int gatheringId);

    // 캘린더 일정 등록
    void insertCalendarEvent(int gatheringId, CalendarEventDto calendarEventDto);

    // 캘린더 일정 조회
    List<CalendarEventDto> getAllCalendarEvent(int gatheringId);

    // 일정 삭제
    void deleteCalendarEvent(int eventId);

    // 일정 수정
    void updateCalendarEvent(CalendarEventDto calendarEventDto);

    // 갤러리 게시글 삽입
    void insertGalleryPost(int gatheringId, int memberId, GalleryPostDto galleryPostDto, List<MultipartFile> multipartFiles);

    // 이미지 조회
    List<GalleryImageResponseDto> getAllImage(int gatheringId);

    // 게시글 상세 조회
    GalleryPostResponseDto getPost(int postId);

    // 댓글 작성
    String insertGalleryComment(int postId, int gatheringId, int memberId, String content);

    // gathering_member_id 조회
    int getGatheringMemberId(int memberId, int gatheringId);

    // 모임 관심사 조회
    List<String> getGatheringInterest(int gatheringId);

    // 공지사항 조회
    List<GalleryPostDto> getNotice(int gatheringId);

    // 모임계좌번호 조회
    String getGatheringAccountNumber(int gatheringId);

    // 모임계좌거래내역 조회
    List<GatheringTransactionDto> getGatheringTransaction(String accountNumber);

    // 사용자 전체 계좌 조회
    List<AccountDto> getAllAccountByPersonalIdNumber(String personaIdNumber);

    // 회비확인
    int getGatheringPaymentAmount(int gatheringId);

    // 화비납입
    void paymentTransfer(AccountTransferDto accountTransferDto, int gatheringId, int memberId);

    // 회비납부여부 확인
    boolean isPaymentMade(int gatheringId, int memberId);

    // 모임계좌 잔액 조회
    int getGatheringBalance(int gatheringId);

    // 모임계좌 출금(계좌이체)
    void gatheringTransfer(AccountTransferDto accountTransferDto);

    // 카드혜택조회
    List<CardBenefitDto> getCardBenefit(int gatheringId);
}
