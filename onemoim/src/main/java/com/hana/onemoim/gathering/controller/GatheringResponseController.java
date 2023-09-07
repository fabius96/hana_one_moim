package com.hana.onemoim.gathering.controller;

import com.hana.onemoim.gathering.dto.GatheringDto;
import com.hana.onemoim.gathering.service.GatheringService;
import com.hana.onemoim.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GatheringResponseController {

    private final GatheringService gatheringService;

    // 내 모임 페이지 조회(로그인 O)
    @GetMapping("/gathering/gathering-info")
    public ModelAndView showGatheringInfo(HttpSession httpSession,
                                          HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        modelAndView.setViewName("/gathering/gathering-info");
        List<GatheringDto> gatheringDtoList = gatheringService.findAllGatheringByMemberId(memberDto.getMemberId());
        modelAndView.addObject("gatherings", gatheringDtoList);
        return modelAndView;
    }

    // 모임 검색
    @GetMapping("/gathering/gathering-search")
    public ModelAndView searchGathering(@RequestParam String keyword) {
        ModelAndView modelAndView = new ModelAndView("/gathering/search-result");
        List<GatheringDto> gatheringDtoList = gatheringService.findAllGatheringByKeyword(keyword);
        int gatheringCount = gatheringService.countGatheringByKeyword(keyword);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("gatherings", gatheringDtoList);
        modelAndView.addObject("gatheringCount", gatheringCount);
        return modelAndView;
    }

    // 새로운 모임 만들기 페이지 조회(로그인 O)
    @GetMapping("/gathering/gathering-create")
    public ModelAndView showGatheringCreate(HttpSession httpSession,
                                            HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        modelAndView.setViewName("/gathering/gathering-create");
        return modelAndView;
    }

    // 모임 카드 개설 페이지 조회(로그인 O)
    @GetMapping("/gathering/card-opening")
    public ModelAndView showCardOpening(HttpSession httpSession,
                                        HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        modelAndView.setViewName("/gathering/card-opening");
        return modelAndView;
    }

    // 모임카드혜택설정 페이지 조회(로그인 O)
    @GetMapping("/gathering/card-opening-setting")
    public ModelAndView showCardOpeningSetting(HttpSession httpSession,
                                               HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        modelAndView.setViewName("/gathering/card-opening-setting");
        return modelAndView;
    }

    // 관심사 설정 페이지 조회(로그인 O)
    @GetMapping("/gathering/gathering-interest")
    public ModelAndView getMemberInterest(HttpSession httpSession,
                                          HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        modelAndView.setViewName("/gathering/gathering-interest");
        return modelAndView;
    }

    // 모임분류 페이지 조회
    @GetMapping("/gathering/gathering-category")
    public ModelAndView showGatheringCategory(@RequestParam String interest) {
        ModelAndView modelAndView = new ModelAndView("/gathering/gathering-category");
        List<GatheringDto> gatheringDtoList = gatheringService.findGatheringByInterest(interest);
        modelAndView.addObject("interest", interest);
        modelAndView.addObject("gatherings", gatheringDtoList);
        return modelAndView;
    }

    // 모임 추천 페이지 조회(로그인 O)
    @GetMapping("/gathering/gathering-recommend")
    public ModelAndView showGatheringRecommend(HttpSession httpSession,
                                               HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        List<GatheringDto> gatheringDtoList = gatheringService.findGatheringByMemberInterest(memberDto.getMemberId());
        modelAndView.addObject("gatherings", gatheringDtoList);
        modelAndView.setViewName("/gathering/gathering-recommend");
        return modelAndView;
    }
}