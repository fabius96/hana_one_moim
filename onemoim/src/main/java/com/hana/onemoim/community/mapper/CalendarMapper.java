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
}
