package com.hana.onemoim.community.controller;

import com.hana.onemoim.account.dto.AccountTransferDto;
import com.hana.onemoim.community.dto.CalendarEventDto;
import com.hana.onemoim.community.dto.GalleryPostDto;
import com.hana.onemoim.community.service.CommunityService;
import com.hana.onemoim.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
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
                                                 @RequestBody CalendarEventDto calendarEventDto) {
        communityService.insertCalendarEvent(gatheringId, calendarEventDto);
        return ResponseEntity.ok().body(Map.of("message", "일정이 정상적으로 등록되었습니다."));
    }

    // 캘린더 일정 삭제
    @DeleteMapping("/community/{gatheringId}/calendar")
    public ResponseEntity<?> deleteCalendarEvent(@PathVariable int gatheringId,
                                                 @RequestParam int eventId) {
        communityService.deleteCalendarEvent(eventId);
        return ResponseEntity.ok().body(Map.of("message", "일정이 정상적으로 삭제되었습니다."));
    }

    // 캘린더 일정 수정
    @PutMapping("/community/{gatheringId}/calendar")
    public ResponseEntity<?> updateCalendarEvent(@PathVariable int gatheringId,
                                                 @RequestBody CalendarEventDto calendarEventDto) {
        communityService.updateCalendarEvent(calendarEventDto);
        return ResponseEntity.ok().body(Map.of("message", "일정이 정상적으로 수정되었습니다."));
    }

    // 갤러리 게시글 작성
    @PostMapping("/community/{gatheringId}/gallery")
    public ResponseEntity<?> insertGalleryPost(@PathVariable int gatheringId,
                                               HttpSession httpSession,
                                               GalleryPostDto galleryPostDto,
                                               @RequestParam("galleryImage") List<MultipartFile> multipartFiles) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        communityService.insertGalleryPost(gatheringId, memberDto.getMemberId(), galleryPostDto, multipartFiles);
        return ResponseEntity.ok().body(Map.of("message", "게시글 등록이 정상적으로 완료되었습니다."));
    }

    // 갤러리 댓글 작성
    @PostMapping("/community/{gatheringId}/gallery/comment")
    public ResponseEntity<?> insertGalleryComment(@PathVariable int gatheringId,
                                                  HttpSession httpSession,
                                                  @RequestParam("postId") int postId,
                                                  @RequestParam("content") String content) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        String memberName = communityService.insertGalleryComment(postId, gatheringId, memberDto.getMemberId(), content);
        return ResponseEntity.ok().body(Map.of("memberName", memberName));
    }

    // 모임회비납부
    @PostMapping("/community/{gatheringId}/payment-hana")
    public ModelAndView gatheringAccountTransfer(@PathVariable int gatheringId,
                                                 HttpSession httpSession,
                                                 @ModelAttribute AccountTransferDto accountTransferDto) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        if (memberDto == null) {
            return modelAndView;
        }
        modelAndView.setViewName("/community/payment-ok");
        communityService.paymentTransfer(accountTransferDto, gatheringId, memberDto.getMemberId());
        return modelAndView;
    }

    // 출금하기
    @PostMapping("/community/{gatheringId}/withdrawal-hana")
    public ModelAndView gatheringAccountWithdrawal(@PathVariable int gatheringId,
                                                 HttpSession httpSession,
                                                 @ModelAttribute AccountTransferDto accountTransferDto) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        if (memberDto == null) {
            return modelAndView;
        }
        modelAndView.setViewName("/community/withdrawal-ok");
        communityService.gatheringTransfer(accountTransferDto);
        return modelAndView;
    }
}
