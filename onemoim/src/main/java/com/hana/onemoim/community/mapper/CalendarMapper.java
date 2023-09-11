package com.hana.onemoim.community.mapper;

import com.hana.onemoim.community.dto.CalendarEventDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalendarMapper {

    // 캘린더 일정 삽입
    void insertCalendarEvent(CalendarEventDto calendarEventDto);
}
