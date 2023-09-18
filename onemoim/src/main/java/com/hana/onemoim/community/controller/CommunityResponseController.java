package com.hana.onemoim.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hana.onemoim.community.dto.CalendarEventDto;
import com.hana.onemoim.community.dto.CommunityInfoDto;
import com.hana.onemoim.community.dto.CommunityMainDto;
import com.hana.onemoim.community.dto.GalleryPostResponseDto;
import com.hana.onemoim.community.service.CommunityService;
import com.hana.onemoim.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityResponseController {

    private final CommunityService communityService;

    // 커뮤니티 메인 페이지 조회
    @GetMapping("/community/{gatheringId}")
    public ModelAndView showCommunityMain(HttpSession httpSession,
                                          HttpServletRequest httpServletRequest,
                                          RedirectAttributes redirectAttributes,
                                          @PathVariable int gatheringId) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        CommunityMainDto communityMainDto = communityService.searchCommunityInfo(gatheringId, memberDto.getMemberId());

        if (communityMainDto.isRedirectRequired()) {
            String referer = httpServletRequest.getHeader("Referer");
            ModelAndView errorModelAndView = new ModelAndView("redirect:" + referer);
            redirectAttributes.addFlashAttribute("message", communityMainDto.getMessage());
            return errorModelAndView;
        }

        modelAndView.setViewName("/community/community-main");
        modelAndView.addObject("gathering", communityMainDto.getGatheringDto());
        modelAndView.addObject("gatheringMemberId", communityService.getGatheringMemberId(memberDto.getMemberId(), gatheringId));
        modelAndView.addObject("galleryImageData", communityService.getAllImage(gatheringId));
        modelAndView.addObject("noticeData", communityService.getNotice(gatheringId));
        modelAndView.addObject("gatheringId", communityMainDto.getGatheringDto().getGatheringId());
        return modelAndView;
    }

    // 커뮤니티 모임 정보 페이지 조회
    @GetMapping("/community/{gatheringId}/info")
    public ModelAndView showCommunityInfo(HttpSession httpSession,
                                          HttpServletRequest httpServletRequest,
                                          @PathVariable int gatheringId) {
        ModelAndView modelAndView = new ModelAndView("/signin");

        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }
        modelAndView.setViewName("/community/community-info");
        modelAndView.addObject("loggedInMemberId", memberDto.getMemberId());

        CommunityInfoDto communityInfoDto = communityService.getCommunityInfo(gatheringId);
        modelAndView.addObject("gathering", communityInfoDto.getGatheringDto());
        modelAndView.addObject("gatheringId", communityInfoDto.getGatheringDto().getGatheringId());
        modelAndView.addObject("gatheringLeaderId", communityInfoDto.getGatheringLeaderId());
        modelAndView.addObject("gatheringMembers", communityInfoDto.getGatheringMemberDtoList());
        return modelAndView;
    }

    // 커뮤니티 일정 페이지 조회
    @GetMapping("/community/{gatheringId}/calendar")
    public ModelAndView showCommunityCalendar(HttpSession httpSession,
                                              HttpServletRequest httpServletRequest,
                                              @PathVariable int gatheringId) {
        ModelAndView modelAndView = new ModelAndView("/signin");

        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        List<CalendarEventDto> events = communityService.getAllCalendarEvent(gatheringId);
        ObjectMapper objectMapper = new ObjectMapper();
        String eventsJson;
        try {
            eventsJson = objectMapper.writeValueAsString(events);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        modelAndView.setViewName("/community/community-calendar");
        modelAndView.addObject("eventsJson", eventsJson);
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.addObject("loggedInMemberId", memberDto.getMemberId());

        return modelAndView;
    }

    // 커뮤니티 갤러리 페이지 조회
    @GetMapping("/community/{gatheringId}/gallery")
    public ModelAndView showCommunityGallery(HttpSession httpSession,
                                             HttpServletRequest httpServletRequest,
                                             @PathVariable int gatheringId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        ModelAndView modelAndView = new ModelAndView("/signin");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }
        modelAndView.setViewName("/community/community-gallery");
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.addObject("gatheringMemberId", communityService.getGatheringMemberId(memberDto.getMemberId(), gatheringId));
        modelAndView.addObject("galleryImageData", communityService.getAllImage(gatheringId));
        return modelAndView;
    }

    // 갤러리 게시글 상세 조회(모달)
    @GetMapping("/community/{gatheringId}/gallery-post")
    public ResponseEntity<?> showGalleryPost(HttpSession httpSession,
                                             @PathVariable int gatheringId,
                                             @RequestParam int postId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        GalleryPostResponseDto galleryPostResponseDto = communityService.getPost(postId);
        return ResponseEntity.ok().body(galleryPostResponseDto);
    }

    // 모임 관심사 조회
    @GetMapping("/community/{gatheringId}/interest")
    public ResponseEntity<?> getGatheringInterest(@PathVariable int gatheringId){
        List<String> gatheringInterestList = communityService.getGatheringInterest(gatheringId);
        return ResponseEntity.ok().body(gatheringInterestList);
    }
}
