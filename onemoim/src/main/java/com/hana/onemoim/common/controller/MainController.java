package com.hana.onemoim.common.controller;

import com.hana.onemoim.gathering.dto.GatheringDto;
import com.hana.onemoim.gathering.service.GatheringService;
import com.hana.onemoim.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final GatheringService gatheringService;

    @GetMapping({"/before_login_main", "/"})
    public String showBeforeLoginMain() {
        return "before_login_main";
    }

    @GetMapping("/after-login-main")
    public ModelAndView showAfterLoginMain(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("/after-login-main");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        List<GatheringDto> gatheringDtoList;
        if (memberDto == null) {
            gatheringDtoList = gatheringService.findGatheringByMemberInterest(0, true);
        } else {
            gatheringDtoList = gatheringService.findGatheringByMemberInterest(memberDto.getMemberId(), true);
        }
        modelAndView.addObject("gatherings", gatheringDtoList);
        return modelAndView;
    }
}
