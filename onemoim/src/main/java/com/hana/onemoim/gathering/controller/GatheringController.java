package com.hana.onemoim.gathering.controller;

import com.hana.onemoim.gathering.dto.CardBenefitWrapper;
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

    // 모임 카드 개설
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

    // 카드 혜택 설정
    @PostMapping("/gathering/card-opening-setting")
    public ModelAndView settingCardBenefit(@ModelAttribute CardBenefitWrapper cardBenefitWrapper,
                                           @RequestParam int gatheringCardId,
                                           @RequestParam int gatheringId,
                                           HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        if (memberDto != null) {
            modelAndView.setViewName("/gathering/gathering-interest");
            modelAndView.addObject("gatheringId", gatheringId);
            gatheringService.settingCardBenefit(cardBenefitWrapper, gatheringCardId);
        }

        return modelAndView;
    }

    // 관심사 설정 페이지 조회
    @GetMapping("/gathering/gathering-interest")
    public ModelAndView getMemberInterest() {
        return new ModelAndView("/gathering/gathering-interest");
    }

    //관심사 설정
    @PostMapping("/gathering/gathering-interest")
    public ModelAndView registerMemberInterest(HttpSession httpSession,
                                               @RequestParam int gatheringId,
                                               @RequestParam List<String> interestNames) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        if (memberDto != null) {
            gatheringService.registerGatheringInterest(gatheringId, interestNames);
            modelAndView.setViewName("/gathering/gathering-create-ok");
        }
        return modelAndView;
    }

    // 모임분류 페이지 조회
    @GetMapping("/gathering/gathering-category")
    public ModelAndView showGatheringCategory(@RequestParam String interest){
        ModelAndView modelAndView = new ModelAndView("/gathering/gathering-category");
        List<GatheringDto> gatheringDtoList = gatheringService.findGatheringByInterest(interest);
        modelAndView.addObject("interest",interest);
        modelAndView.addObject("gatherings", gatheringDtoList);
        return modelAndView;
    }

    // 모임 추천 페이지 조회
    @GetMapping("/gathering/gathering-recommend")
    public ModelAndView showGatheringRecommend(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("/gathering/gathering-recommend");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        List<GatheringDto> gatheringDtoList;
        if (memberDto == null) {
            gatheringDtoList = gatheringService.findGatheringByMemberInterest(0, false);
        } else {
            gatheringDtoList = gatheringService.findGatheringByMemberInterest(memberDto.getMemberId(), false);
        }
        modelAndView.addObject("gatherings", gatheringDtoList);
        return modelAndView;
    }
}