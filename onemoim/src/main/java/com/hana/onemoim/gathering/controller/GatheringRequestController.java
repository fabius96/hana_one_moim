package com.hana.onemoim.gathering.controller;

import com.hana.onemoim.gathering.dto.CardBenefitWrapper;
import com.hana.onemoim.gathering.dto.GatheringCreateDto;
import com.hana.onemoim.gathering.service.GatheringService;
import com.hana.onemoim.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GatheringRequestController {

    private final GatheringService gatheringService;

    // 모임 개설(모임 개설 Process 01)
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

        modelAndView.setViewName("/gathering/gathering-interest");
        return modelAndView;
    }

    // 관심사 설정(모임 개설 Process 02)
    @PostMapping("/gathering/gathering-interest")
    public ModelAndView registerMemberInterest(HttpSession httpSession,
                                               @RequestParam(required = false) int gatheringId,
                                               @RequestParam(required = false) String gatheringName,
                                               @RequestParam List<String> interestNames) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        if (memberDto != null) {
            gatheringService.registerGatheringInterest(gatheringId, interestNames);
            modelAndView.setViewName("/account/account-opening");

            modelAndView.addObject("gatheringName", gatheringName);
            modelAndView.addObject("gatheringId", gatheringId);
            modelAndView.addObject("productName", "하나원모임");
        }
        return modelAndView;
    }

    // 모임 카드 개설(모임 개설 Process 04)
    @PostMapping("/gathering/card-opening")
    public ModelAndView makeGatheringCard(@RequestParam int gatheringId,
                                          @RequestParam String accountNumber,
                                          @RequestParam String gatheringName) {
        ModelAndView modelAndView = new ModelAndView("/gathering/card-opening-setting");
        int gatheringCardId = gatheringService.createdGatheringCard(gatheringId, accountNumber, gatheringName);
        modelAndView.addObject("gatheringCardId", gatheringCardId);
        modelAndView.addObject("gatheringId", gatheringId);
        return modelAndView;
    }

    // 카드 혜택 설정(모임 개설 Process 05)
    @PostMapping("/gathering/card-opening-setting")
    public ModelAndView settingCardBenefit(@ModelAttribute CardBenefitWrapper cardBenefitWrapper,
                                           @RequestParam int gatheringCardId,
                                           @RequestParam int gatheringId,
                                           HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        if (memberDto != null) {
            modelAndView.setViewName("/gathering/gathering-create-ok");
            modelAndView.addObject("gatheringId", gatheringId);
            gatheringService.settingCardBenefit(cardBenefitWrapper, gatheringCardId);
        }

        return modelAndView;
    }
}