package com.hana.onemoim.gathering.controller;

import com.hana.onemoim.member.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class GatheringController {

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
}
