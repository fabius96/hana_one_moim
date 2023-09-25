package com.hana.onemoim.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.community.dto.*;
import com.hana.onemoim.community.service.CommunityService;
import com.hana.onemoim.gathering.dto.CardBenefitDto;
import com.hana.onemoim.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommunityResponseController {

    private final CommunityService communityService;

    // 커뮤니티 메인 페이지 조회
    @GetMapping("/community/{gatheringId}")
    public ModelAndView showCommunityMain(HttpSession httpSession,
                                          HttpServletRequest httpServletRequest,
                                          RedirectAttributes redirectAttributes,
                                          @PathVariable int gatheringId) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        CommunityMainDto communityMainDto = communityService.searchCommunityInfo(gatheringId, memberDto.getMemberId());

        if (communityMainDto.isRedirectRequired()) {
            String referer = httpServletRequest.getHeader("Referer");
            ModelAndView errorModelAndView = new ModelAndView("redirect:" + referer);
            redirectAttributes.addFlashAttribute("message", communityMainDto.getMessage());
            return errorModelAndView;
        }

        modelAndView.setViewName("/community/community-main");
        modelAndView.addObject("gathering", communityMainDto.getGatheringDto());
        modelAndView.addObject("gatheringMemberId", communityService.getGatheringMemberId(memberDto.getMemberId(), gatheringId));
        modelAndView.addObject("galleryImageData", communityService.getAllImage(gatheringId));
        modelAndView.addObject("noticeData", communityService.getNotice(gatheringId));
        modelAndView.addObject("gatheringId", communityMainDto.getGatheringDto().getGatheringId());
        return modelAndView;
    }

    // 커뮤니티 모임 정보 페이지 조회
    @GetMapping("/community/{gatheringId}/info")
    public ModelAndView showCommunityInfo(HttpSession httpSession,
                                          HttpServletRequest httpServletRequest,
                                          @PathVariable int gatheringId) {
        ModelAndView modelAndView = new ModelAndView("/signin");

        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }
        modelAndView.setViewName("/community/community-info");

        CommunityInfoDto communityInfoDto = communityService.getCommunityInfo(gatheringId);
        modelAndView.addObject("gathering", communityInfoDto.getGatheringDto());
        modelAndView.addObject("gatheringId", communityInfoDto.getGatheringDto().getGatheringId());
        modelAndView.addObject("loggedInMemberId", memberDto.getMemberId());
        modelAndView.addObject("gatheringLeaderId", communityInfoDto.getGatheringLeaderId());
        modelAndView.addObject("gatheringMembers", communityInfoDto.getGatheringMemberDtoList());
        return modelAndView;
    }

    // 커뮤니티 일정 페이지 조회
    @GetMapping("/community/{gatheringId}/calendar")
    public ModelAndView showCommunityCalendar(HttpSession httpSession,
                                              HttpServletRequest httpServletRequest,
                                              @PathVariable int gatheringId) {
        ModelAndView modelAndView = new ModelAndView("/signin");

        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        List<CalendarEventDto> events = communityService.getAllCalendarEvent(gatheringId);
        ObjectMapper objectMapper = new ObjectMapper();
        String eventsJson;
        try {
            eventsJson = objectMapper.writeValueAsString(events);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        modelAndView.setViewName("/community/community-calendar");
        modelAndView.addObject("eventsJson", eventsJson);
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.addObject("loggedInMemberId", memberDto.getMemberId());

        return modelAndView;
    }

    // 커뮤니티 갤러리 페이지 조회
    @GetMapping("/community/{gatheringId}/gallery")
    public ModelAndView showCommunityGallery(HttpSession httpSession,
                                             HttpServletRequest httpServletRequest,
                                             @PathVariable int gatheringId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        ModelAndView modelAndView = new ModelAndView("/signin");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }
        modelAndView.setViewName("/community/community-gallery");
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.addObject("gatheringMemberId", communityService.getGatheringMemberId(memberDto.getMemberId(), gatheringId));
        modelAndView.addObject("galleryImageData", communityService.getAllImage(gatheringId));
        return modelAndView;
    }

    // 갤러리 게시글 상세 조회(모달)
    @GetMapping("/community/{gatheringId}/gallery-post")
    public ResponseEntity<?> showGalleryPost(HttpSession httpSession,
                                             @PathVariable int gatheringId,
                                             @RequestParam int postId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        GalleryPostResponseDto galleryPostResponseDto = communityService.getPost(postId);
        return ResponseEntity.ok().body(galleryPostResponseDto);
    }

    // 모임 관심사 조회
    @GetMapping("/community/{gatheringId}/interest")
    public ResponseEntity<?> getGatheringInterest(@PathVariable int gatheringId) {
        List<String> gatheringInterestList = communityService.getGatheringInterest(gatheringId);
        return ResponseEntity.ok().body(gatheringInterestList);
    }

    // 커뮤니티 계좌 페이지 조회
    @GetMapping("/community/{gatheringId}/account")
    public ModelAndView showCommunityAccount(HttpSession httpSession,
                                             HttpServletRequest httpServletRequest,
                                             @PathVariable int gatheringId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        ModelAndView modelAndView = new ModelAndView("/signin");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        CommunityInfoDto communityInfoDto = communityService.getCommunityInfo(gatheringId);
        modelAndView.setViewName("/community/community-account");
        modelAndView.addObject("isPaid", communityService.isPaymentMade(gatheringId, memberDto.getMemberId()));
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.addObject("accountNumber", communityService.getGatheringAccountNumber(gatheringId));
        modelAndView.addObject("gathering", communityInfoDto.getGatheringDto());
        modelAndView.addObject("gatheringMemberId", communityService.getGatheringMemberId(memberDto.getMemberId(), gatheringId));
        modelAndView.addObject("loggedInMemberId", memberDto.getMemberId());
        modelAndView.addObject("gatheringLeaderId", communityInfoDto.getGatheringLeaderId());
        return modelAndView;
    }

    // 계좌이체 내역 조회 메서드
    @GetMapping("/api/community/get-account-transaction")
    @ResponseBody
    public List<GatheringTransactionDto> getAccountTransaction(String accountNumber, @RequestParam int month) {
        return communityService.getGatheringTransaction(accountNumber, month);
    }

    // 계좌이체 내역 조회 메서드
    @GetMapping("/api/community/{gatheringId}/get-account-transaction-top5")
    @ResponseBody
    public List<GatheringTransactionDto> getAccountTransactionTop5(@PathVariable int gatheringId, int month) {
        return communityService.getAccountTransactionTop5(gatheringId, month);
    }

    // 커뮤니티 회비 납입 페이지 조회(하나은행)
    @GetMapping("/community/{gatheringId}/payment-hana")
    public ModelAndView showPaymentHana(HttpSession httpSession,
                                        HttpServletRequest httpServletRequest,
                                        @PathVariable int gatheringId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        ModelAndView modelAndView = new ModelAndView("/signin");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        List<AccountDto> accountDtoList = communityService.getAllAccountByPersonalIdNumber(memberDto.getPersonalIdNumber());

        modelAndView.setViewName("/community/community-payment-hana");
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.addObject("accounts", accountDtoList);
        modelAndView.addObject("paymentAmount", communityService.getGatheringPaymentAmount(gatheringId));
        modelAndView.addObject("accountNumber", communityService.getGatheringAccountNumber(gatheringId));
        modelAndView.addObject("gatheringMemberId", communityService.getGatheringMemberId(memberDto.getMemberId(), gatheringId));
        return modelAndView;
    }

    // 커뮤니티 계좌 이체 페이지 조회(하나은행)
    @GetMapping("/community/{gatheringId}/transfer-hana")
    public ModelAndView showTransferHana(HttpSession httpSession,
                                         HttpServletRequest httpServletRequest,
                                         @PathVariable int gatheringId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        ModelAndView modelAndView = new ModelAndView("/signin");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        List<AccountDto> accountDtoList = communityService.getAllAccountByPersonalIdNumber(memberDto.getPersonalIdNumber());
        modelAndView.setViewName("/community/community-transfer-hana");
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.addObject("accounts", accountDtoList);
        modelAndView.addObject("paymentAmount", communityService.getGatheringPaymentAmount(gatheringId));
        modelAndView.addObject("accountNumber", communityService.getGatheringAccountNumber(gatheringId));
        modelAndView.addObject("gatheringMemberId", communityService.getGatheringMemberId(memberDto.getMemberId(), gatheringId));
        return modelAndView;
    }

    // 커뮤니티 계좌 출금 페이지 조회(하나은행)
    @GetMapping("/community/{gatheringId}/withdrawal-hana")
    public ModelAndView showWithdrawalHana(HttpSession httpSession,
                                           HttpServletRequest httpServletRequest,
                                           @PathVariable int gatheringId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        ModelAndView modelAndView = new ModelAndView("/signin");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        List<AccountDto> accountDtoList = communityService.getAllAccountByPersonalIdNumber(memberDto.getPersonalIdNumber());
        CommunityInfoDto communityInfoDto = communityService.getCommunityInfo(gatheringId);

        modelAndView.setViewName("/community/community-withdrawal-hana");
        modelAndView.addObject("gathering", communityInfoDto.getGatheringDto());
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.addObject("accounts", accountDtoList);
        modelAndView.addObject("balance", communityService.getGatheringBalance(gatheringId));
        modelAndView.addObject("paymentAmount", communityService.getGatheringPaymentAmount(gatheringId));
        modelAndView.addObject("accountNumber", communityService.getGatheringAccountNumber(gatheringId));
        modelAndView.addObject("gatheringMemberId", communityService.getGatheringMemberId(memberDto.getMemberId(), gatheringId));
        return modelAndView;
    }

    // 회비납부 완료 페이지 조회(로그인 O)
    @GetMapping("/community/{gatheringId}/payment-ok")
    public ModelAndView showAccountTransferOk(HttpSession httpSession,
                                              @PathVariable int gatheringId) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            return modelAndView;
        }
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.setViewName("/community/payment-ok");
        return modelAndView;
    }

    // 커뮤니티 카드 페이지 조회
    @GetMapping("/community/{gatheringId}/card")
    public ModelAndView showCommunityCard(HttpSession httpSession,
                                          HttpServletRequest httpServletRequest,
                                          @PathVariable int gatheringId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        ModelAndView modelAndView = new ModelAndView("/signin");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }
        communityService.getCardBenefit(gatheringId);
        CommunityInfoDto communityInfoDto = communityService.getCommunityInfo(gatheringId);

        modelAndView.setViewName("/community/community-card");
        modelAndView.addObject("gathering", communityInfoDto.getGatheringDto());
        modelAndView.addObject("cardBenefit", communityService.getCardBenefit(gatheringId));
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.addObject("gatheringMemberId", communityService.getGatheringMemberId(memberDto.getMemberId(), gatheringId));
        return modelAndView;
    }

    // 카드 소비 데이터 호출
    @GetMapping("/community/{gatheringId}/card-transaction")
    public ResponseEntity<?> getCardTransactionData(@PathVariable int gatheringId, @RequestParam int month) {
        Map<String, Integer> cardTransactionData = communityService.getCardTransactionData(gatheringId, month);
        return ResponseEntity.ok().body(cardTransactionData);
    }

    // 커뮤니티 카드혜택 변경하기 페이지 조회
    @GetMapping("/community/{gatheringId}/card-benefit")
    public ModelAndView showCommunityCardBenefit(HttpSession httpSession,
                                                 HttpServletRequest httpServletRequest,
                                                 @PathVariable int gatheringId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        ModelAndView modelAndView = new ModelAndView("/signin");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }
        communityService.getCardBenefit(gatheringId);
        CommunityInfoDto communityInfoDto = communityService.getCommunityInfo(gatheringId);

        modelAndView.setViewName("/community/community-card-benefit");
        modelAndView.addObject("gathering", communityInfoDto.getGatheringDto());
        modelAndView.addObject("cardBenefit", communityService.getCardBenefit(gatheringId));
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.addObject("gatheringMemberId", communityService.getGatheringMemberId(memberDto.getMemberId(), gatheringId));
        return modelAndView;
    }

    // 카드 혜택 가져오기 메서드
    @GetMapping("/community/{gatheringId}/card-benefit/data")
    @ResponseBody
    public List<CardBenefitDto> getCardBenefitData(@PathVariable int gatheringId) {
        return communityService.getCardBenefit(gatheringId);
    }

    // 카드혜택변경 완료 페이지 조회(로그인 O)
    @GetMapping("/community/{gatheringId}/edit-benefit-ok")
    public ModelAndView showEditBenefitOk(HttpSession httpSession,
                                          @PathVariable int gatheringId) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            return modelAndView;
        }
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.setViewName("/community/edit-benefit-ok");
        return modelAndView;
    }
}
