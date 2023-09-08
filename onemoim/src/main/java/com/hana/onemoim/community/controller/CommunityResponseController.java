package com.hana.onemoim.community.controller;

import com.hana.onemoim.gathering.dto.GatheringDto;
import com.hana.onemoim.gathering.service.GatheringService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class CommunityResponseController {

    private final GatheringService gatheringService;

    // 모임 메인 페이지 조회
    @GetMapping("/community/community-main")
    public ModelAndView showGatheringMain(@RequestParam("gatheringId") int gatheringId) {
        ModelAndView modelAndView = new ModelAndView("/community/community-main");
        GatheringDto gatheringDto = gatheringService.findGatheringByGatheringId(false, gatheringId);
        modelAndView.addObject("gathering", gatheringDto);
        return modelAndView;
    }

}
