package com.hana.onemoim.community.controller;

import com.hana.onemoim.community.service.CommunityService;
import com.hana.onemoim.gathering.dto.GatheringDto;
import com.hana.onemoim.gathering.service.GatheringService;
import com.hana.onemoim.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CommunityResponseController {

    private final GatheringService gatheringService;
    private final CommunityService communityService;

    // 커뮤니티 메인 페이지 조회
    @GetMapping("/community/{gatheringId}")
    public ModelAndView showGatheringMain(HttpSession httpSession,
                                          HttpServletRequest httpServletRequest,
                                          RedirectAttributes redirectAttributes,
                                          @PathVariable int gatheringId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        int memberStatusCode = communityService.checkMemberStatusCode(gatheringId, memberDto.getMemberId());

        // 원래 사용자가 있던 페이지 (Referer 헤더에서 가져옴)
        String referer = httpServletRequest.getHeader("Referer");

        if (memberStatusCode == 71) {
            ModelAndView errorModelAndView = new ModelAndView("redirect:" + referer);
            redirectAttributes.addFlashAttribute("message", "현재 활동이 정지된 모임원입니다. <br/> 모임장에게 문의하세요");
            return errorModelAndView;
        } else if (memberStatusCode == 72) {
            ModelAndView errorModelAndView = new ModelAndView("redirect:" + referer);
            redirectAttributes.addFlashAttribute("message", "승인 대기 상태의 모임원입니다.");
            return errorModelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("/community/community-main");
        GatheringDto gatheringDto = gatheringService.findGatheringByGatheringId(false, gatheringId);
        modelAndView.addObject("gathering", gatheringDto);
        return modelAndView;
    }

    // 커뮤니티 모임 정보 페이지 조회
    @GetMapping("/community/{gatheringId}/info")
    public ModelAndView showCommunityInfo(@PathVariable int gatheringId) {
        ModelAndView modelAndView = new ModelAndView("/community/community-info");
        modelAndView.addObject("gathering",gatheringService.findGatheringByGatheringId(false, gatheringId));
        modelAndView.addObject("gatheringLeaderId",gatheringService.getGatheringLeaderId(gatheringId));
        modelAndView.addObject("gatheringMembers", communityService.findGatheringMemberByGatheringId(gatheringId));
        return modelAndView;
    }
}
