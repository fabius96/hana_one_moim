package com.hana.onemoim.community.controller;

import com.hana.onemoim.community.dto.CalendarEventDto;
import com.hana.onemoim.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommunityRequestController {

    private final CommunityService communityService;

    // 모임원 상태 코드 변경
    @PatchMapping("/community/update-status")
    public ResponseEntity<?> updateGatheringMemberStatusCode(@RequestParam("memberStatusCode") int memberStatusCode,
                                                             @RequestParam("memberId") int memberId,
                                                             @RequestParam("gatheringId") int gatheringId) {
        communityService.updateMemberStatusCode(memberStatusCode, memberId, gatheringId);

        return ResponseEntity.ok().body(Map.of("message", "모임원 상태가 정상적으로 변경되었습니다."));
    }

    // 캘린더 일정 삽입
    @PostMapping("/community/{gatheringId}/calendar")
    public ResponseEntity<?> insertCalendarEvent(@PathVariable int gatheringId,
                                                 @RequestBody CalendarEventDto calendarEventDto){
        communityService.insertCalendarEvent(gatheringId, calendarEventDto);
        return ResponseEntity.ok().body(Map.of("message", "일정이 정상적으로 등록되었습니다."));
    }

    // 캘린더 일정 삭제
    @DeleteMapping("/community/{gatheringId}/calendar")
    public ResponseEntity<?> deleteCalendarEvent(@PathVariable int gatheringId,
                                                 @RequestParam int eventId){
        communityService.deleteCalendarEvent(eventId);
        return ResponseEntity.ok().body(Map.of("message","일정이 정상적으로 삭제되었습니다."));
    }

    // 캘린더 일정 수정
    @PutMapping("/community/{gatheringId}/calendar")
    public ResponseEntity<?> updateCalendarEvent(@PathVariable int gatheringId,
                                                 @RequestBody CalendarEventDto calendarEventDto){
        communityService.updateCalendarEvent(calendarEventDto);
        return ResponseEntity.ok().body(Map.of("message","일정이 정상적으로 수정되었습니다."));
    }
}
