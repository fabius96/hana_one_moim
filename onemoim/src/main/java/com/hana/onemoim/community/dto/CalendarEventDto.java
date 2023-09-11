package com.hana.onemoim.community.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarEventDto {
    private int eventId; // 일정ID
    private int gatheringId; // 모임ID
    private String eventTitle; // 일정명
    private String eventStartDate; // 일정 시작 일자
    private String eventEndDate; // 일정 종료 일자
    private boolean alldayYn; // 종일 진행 여부
    private String eventDescription; // 일정 설명
}
