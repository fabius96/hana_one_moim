package com.hana.onemoim.gathering.controller;

import com.hana.onemoim.gathering.dto.GatheringCreateDto;
import com.hana.onemoim.gathering.service.GatheringService;
import com.hana.onemoim.member.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class GatheringController {

    private GatheringService gatheringService;

    public GatheringController(GatheringService gatheringService) {
        this.gatheringService = gatheringService;
    }

    // 내 모임 페이지 조회
    @GetMapping("/gathering/gathering-info")
    public ModelAndView showGatheringInfo(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto != null) {
            modelAndView.setViewName("/gathering/gathering-info");
        }

        return modelAndView;
    }

    // 새로운 모임 만들기 페이지 조회
    @GetMapping("/gathering/gathering-create")
    public ModelAndView showGatheringCreate(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto != null) {
            modelAndView.setViewName("/gathering/gathering-create");
        }

        return modelAndView;
    }

    // 모임 개설
    @PostMapping("/gathering/gathering-create")
    public ModelAndView createGathering(HttpSession httpSession,
                                        @ModelAttribute GatheringCreateDto gatheringCreateDto,
                                        @RequestParam("gatheringImage") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView("/gathering/gathering-create-ok");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        gatheringCreateDto.setGatheringLeaderId(memberDto.getMemberId());
        gatheringService.createGathering(gatheringCreateDto, file);

        return modelAndView;
    }
}
