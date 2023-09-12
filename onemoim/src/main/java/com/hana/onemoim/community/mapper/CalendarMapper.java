package com.hana.onemoim.community.mapper;

import com.hana.onemoim.community.dto.CalendarEventDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarMapper {

    // 캘린더 일정 삽입
    void insertCalendarEvent(CalendarEventDto calendarEventDto);

    // 캘린더 조회
    List<CalendarEventDto> selectCalendarEventByGatheringId(int gatheringId);

    // 일정 삭제
    void deleteCalendarEventByEventId(int eventId);

    // 일정 수정
    void updateCalendarEvent(CalendarEventDto calendarEventDto);
}
