package com.hana.onemoim.gathering.controller;

import com.hana.onemoim.gathering.dto.GatheringCreateDto;
import com.hana.onemoim.gathering.dto.GatheringDto;
import com.hana.onemoim.gathering.service.GatheringService;
import com.hana.onemoim.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GatheringController {

    private final GatheringService gatheringService;

    // 내 모임 페이지 조회
    @GetMapping("/gathering/gathering-info")
    public ModelAndView showGatheringInfo(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            return modelAndView;
        }

        modelAndView.setViewName("/gathering/gathering-info");
        List<GatheringDto> gatheringDtoList = gatheringService.findAllGatheringByMemberId(memberDto.getMemberId());
        modelAndView.addObject("gatherings", gatheringDtoList);
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
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            return modelAndView;
        }

        gatheringCreateDto.setMemberId(memberDto.getMemberId());
        gatheringCreateDto.setMemberName(memberDto.getName());
        gatheringCreateDto.setGatheringLeaderId(memberDto.getMemberId());
        gatheringService.createGathering(gatheringCreateDto, file);
        modelAndView.addObject("gatheringName", gatheringCreateDto.getGatheringName());
        modelAndView.addObject("gatheringId", gatheringCreateDto.getGatheringId());
        modelAndView.addObject("productName", "하나원모임");

        modelAndView.setViewName("/account/account-opening");
        return modelAndView;
    }

    // 모임 카드 개설 페이지 조회
    @GetMapping("/gathering/card-opening")
    public ModelAndView showCardOpening(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto != null) {
            modelAndView.setViewName("/gathering/card-opening");
        }

        return modelAndView;
    }

    // 금융상품 개설
    @PostMapping("/gathering/card-opening")
    public ModelAndView makeGatheringCard(@RequestParam int gatheringId,
                                          @RequestParam String accountNumber,
                                          @RequestParam String gatheringName) {
        ModelAndView modelAndView = new ModelAndView("/gathering/gathering-create-ok");
        gatheringService.createdGatheringCard(gatheringId, accountNumber, gatheringName);
        return modelAndView;
    }

    // 모임카드혜택설정 페이지 조회
    @GetMapping("/gathering/card-opening-setting")
    public ModelAndView showCardOpeningSetting(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("/gathering/card-opening-setting");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto != null) {
            modelAndView.setViewName("/gathering/card-opening-setting");
        }

        return modelAndView;
    }
}