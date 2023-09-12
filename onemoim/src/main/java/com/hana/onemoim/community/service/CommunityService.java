package com.hana.onemoim.community.service;

import com.hana.onemoim.community.dto.CalendarEventDto;
import com.hana.onemoim.community.dto.CommunityInfoDto;
import com.hana.onemoim.community.dto.CommunityMainDto;
import com.hana.onemoim.community.dto.GatheringMemberDto;

import java.util.List;

public interface CommunityService {

    // 모임원 조회
    List<GatheringMemberDto> findGatheringMemberByGatheringId(int gatheringId);

    // 모임원 상태 코드 확인
    int checkMemberStatusCode(int gatheringId, int memberId);

    // 모임 메인 페이지 조회
    CommunityMainDto searchCommunityInfo(int gatheringId, int memberId);

    // 커뮤니티 - 모임 정보 조회
    CommunityInfoDto getCommunityInfo( int gatheringId);

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
}
