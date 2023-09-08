package com.hana.onemoim.community.controller;

import com.hana.onemoim.community.service.CommunityService;
import com.hana.onemoim.gathering.dto.GatheringDto;
import com.hana.onemoim.gathering.service.GatheringService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class CommunityResponseController {

    private final GatheringService gatheringService;
    private final CommunityService communityService;

    // 커뮤니티 메인 페이지 조회
    @GetMapping("/community/{gatheringId}")
    public ModelAndView showGatheringMain(@PathVariable int gatheringId) {
        ModelAndView modelAndView = new ModelAndView("/community/community-main");
        GatheringDto gatheringDto = gatheringService.findGatheringByGatheringId(false, gatheringId);
        modelAndView.addObject("gathering", gatheringDto);
        return modelAndView;
    }

    // 커뮤니티 모임 정보 페이지 조회
    @GetMapping("/community/{gatheringId}/info")
    public ModelAndView showCommunityInfo(@PathVariable int gatheringId) {
        ModelAndView modelAndView = new ModelAndView("/community/community-info");
        modelAndView.addObject("gatheringLeaderId",gatheringService.getGatheringLeaderId(gatheringId));
        modelAndView.addObject("gatheringMembers", communityService.findGatheringMemberByGatheringId(gatheringId));
        return modelAndView;
    }
}
