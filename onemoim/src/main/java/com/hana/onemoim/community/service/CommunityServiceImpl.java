package com.hana.onemoim.community.service;

import com.hana.onemoim.community.dto.CalendarEventDto;
import com.hana.onemoim.community.dto.CommunityInfoDto;
import com.hana.onemoim.community.dto.CommunityMainDto;
import com.hana.onemoim.community.dto.GatheringMemberDto;
import com.hana.onemoim.community.mapper.CalendarMapper;
import com.hana.onemoim.community.mapper.GatheringMemberMapper;
import com.hana.onemoim.gathering.dto.GatheringDto;
import com.hana.onemoim.gathering.mapper.GatheringMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final GatheringMemberMapper gatheringMemberMapper;
    private final GatheringMapper gatheringMapper;
    private final CalendarMapper calendarMapper;

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

    @Override
    public void insertCalendarEvent(int gatheringId, CalendarEventDto calendarEventDto) {
        System.out.println(calendarEventDto.getEventEndDate());
        try {
            // eventStartDate와 eventEndDate를 Java의 Date 타입으로 변환
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date startDateTime = inputFormat.parse(calendarEventDto.getEventStartDate());
            System.out.println(startDateTime);
            Date endDateTime = inputFormat.parse(calendarEventDto.getEventEndDate());

            // 변환된 Date 객체를 Oracle 데이터베이스 형식의 문자열로 재변환
            SimpleDateFormat oracleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            calendarEventDto.setEventStartDate(oracleFormat.format(startDateTime));
            System.out.println( oracleFormat.format(startDateTime));
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
}
