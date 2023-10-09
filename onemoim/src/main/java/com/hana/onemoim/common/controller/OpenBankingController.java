package com.hana.onemoim.common.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.account.dto.AccountTransferDto;
import com.hana.onemoim.account.dto.PaymentTransferWrapper;
import com.hana.onemoim.community.service.CommunityService;
import com.hana.onemoim.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OpenBankingController {

    private final CommunityService communityService;

    // 오픈뱅킹 연동계좌 조회 페이지 조회(로그인 O)
    @GetMapping("/openbanking")
    public ModelAndView showOpenbanking(HttpSession httpSession,
                                        HttpServletRequest httpServletRequest) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }
        RestTemplate restTemplate = new RestTemplate();
        String openBankingUrl = "http://localhost:8081/openbanking/get-registered-account-list?personalIdNumber=" + memberDto.getPersonalIdNumber();
//        String openBankingUrl = "http://4.230.16.107/openbanking/get-registered-account-list?personalIdNumber=" + memberDto.getPersonalIdNumber();
        ResponseEntity<List<AccountDto>> response = restTemplate.exchange(
                openBankingUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AccountDto>>() {
                }
        );
        List<AccountDto> accountDtoList = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        String accountDtoListAsJson = objectMapper.writeValueAsString(accountDtoList);
        modelAndView.addObject("accountDtoList", accountDtoListAsJson);
        modelAndView.setViewName("/account/openbanking");
        return modelAndView;
    }

    // 오픈뱅킹 어카운트인포 페이지 조회(로그인 O)
    @GetMapping("/openbanking/account-info")
    public ModelAndView showAccountInfo(HttpSession httpSession,
                                        HttpServletRequest httpServletRequest) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }
        RestTemplate restTemplate = new RestTemplate();
        String openBankingUrl = "http://localhost:8081/openbanking/get-account-list?personalIdNumber=" + memberDto.getPersonalIdNumber();
//        String openBankingUrl = "http://4.230.16.107/openbanking/get-account-list?personalIdNumber=" + memberDto.getPersonalIdNumber();
        ResponseEntity<List<AccountDto>> response = restTemplate.exchange(
                openBankingUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AccountDto>>() {
                }
        );
        List<AccountDto> accountDtoList = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        String accountDtoListAsJson = objectMapper.writeValueAsString(accountDtoList);
        modelAndView.addObject("accountDtoList", accountDtoListAsJson);
        modelAndView.setViewName("/account/openbanking-account-info");
        return modelAndView;
    }


    // 계좌조회(오픈뱅킹-타행) 페이지 조회(로그인 O)
    @GetMapping("/account/account-info-other")
    public ModelAndView showAccountInfoOther(HttpSession httpSession, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }
        RestTemplate restTemplate = new RestTemplate();
        String openBankingUrl = "http://localhost:8081/openbanking/get-registered-account-list?personalIdNumber=" + memberDto.getPersonalIdNumber();
//        String openBankingUrl = "http://4.230.16.107/openbanking/get-registered-account-list?personalIdNumber=" + memberDto.getPersonalIdNumber();
        ResponseEntity<List<AccountDto>> response = restTemplate.exchange(
                openBankingUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AccountDto>>() {
                }
        );
        List<AccountDto> accountDtoList = response.getBody();
        modelAndView.addObject("accounts", accountDtoList);
        modelAndView.setViewName("/account/account-info-other");
        return modelAndView;
    }

    // 커뮤니티 계좌 입금하기 페이지 조회(오픈뱅킹 - 타행)
    @GetMapping("/community/{gatheringId}/transfer-other")
    public ModelAndView showTransferOther(HttpSession httpSession,
                                          HttpServletRequest httpServletRequest,
                                          @PathVariable int gatheringId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        ModelAndView modelAndView = new ModelAndView("/signin");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        RestTemplate restTemplate = new RestTemplate();
        String openBankingUrl = "http://localhost:8081/openbanking/get-registered-account-list?personalIdNumber=" + memberDto.getPersonalIdNumber();
//        String openBankingUrl = "http://4.230.16.107/openbanking/get-registered-account-list?personalIdNumber=" + memberDto.getPersonalIdNumber();
        ResponseEntity<List<AccountDto>> response = restTemplate.exchange(
                openBankingUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AccountDto>>() {
                }
        );
        List<AccountDto> accountDtoList = response.getBody();

        modelAndView.setViewName("/community/community-transfer-other");
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.addObject("accounts", accountDtoList);
        modelAndView.addObject("paymentAmount", communityService.getGatheringPaymentAmount(gatheringId));
        modelAndView.addObject("accountNumber", communityService.getGatheringAccountNumber(gatheringId));
        modelAndView.addObject("gatheringMemberId", communityService.getGatheringMemberId(memberDto.getMemberId(), gatheringId));
        return modelAndView;
    }

    // 모임회비납부(오픈뱅킹)
    @PostMapping("/community/{gatheringId}/payment-other")
    public ModelAndView gatheringAccountTransfer(@PathVariable int gatheringId,
                                                 HttpSession httpSession,
                                                 @ModelAttribute AccountTransferDto accountTransferDto) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        if (memberDto == null) {
            return modelAndView;
        }

        PaymentTransferWrapper paymentTransferWrapper = PaymentTransferWrapper.builder()
                .accountTransferDto(accountTransferDto)
                .gatheringId(gatheringId)
                .gatheringMemberId(communityService.getGatheringMemberId(memberDto.getMemberId(), gatheringId))
                .build();

        // 오픈뱅킹으로 데이터 전송(오픈뱅킹을 통한 타행계좌 출금)
        RestTemplate restTemplate = new RestTemplate();
        String openBankingUrl = "http://localhost:8081/openbanking/payment-other";
//        String openBankingUrl = "http://4.230.16.107/openbanking/payment-other";
        ResponseEntity<Boolean> response = restTemplate.postForEntity(openBankingUrl, paymentTransferWrapper, Boolean.class);
        boolean transferSuccess = Boolean.TRUE.equals(response.getBody());

        if (transferSuccess) {
            communityService.paymentTransferOpenbanking(accountTransferDto, gatheringId, memberDto.getMemberId()); // 모임계좌 입금처리
            modelAndView.setViewName("/community/payment-ok");
        } else {
            modelAndView.setViewName("/community/payment-fail");
        }
        return modelAndView;
    }

    // 커뮤니티 회비납입 페이지 조회(오픈뱅킹 - 타행)
    @GetMapping("/community/{gatheringId}/payment-other")
    public ModelAndView showPaymentOther(HttpSession httpSession,
                                          HttpServletRequest httpServletRequest,
                                          @PathVariable int gatheringId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        ModelAndView modelAndView = new ModelAndView("/signin");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        RestTemplate restTemplate = new RestTemplate();
        String openBankingUrl = "http://localhost:8081/openbanking/get-registered-account-list?personalIdNumber=" + memberDto.getPersonalIdNumber();
//        String openBankingUrl = "http://4.230.16.107/openbanking/get-registered-account-list?personalIdNumber=" + memberDto.getPersonalIdNumber();
        ResponseEntity<List<AccountDto>> response = restTemplate.exchange(
                openBankingUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AccountDto>>() {
                }
        );
        List<AccountDto> accountDtoList = response.getBody();

        modelAndView.setViewName("/community/community-payment-other");
        modelAndView.addObject("gatheringId", gatheringId);
        modelAndView.addObject("accounts", accountDtoList);
        modelAndView.addObject("paymentAmount", communityService.getGatheringPaymentAmount(gatheringId));
        modelAndView.addObject("accountNumber", communityService.getGatheringAccountNumber(gatheringId));
        modelAndView.addObject("gatheringMemberId", communityService.getGatheringMemberId(memberDto.getMemberId(), gatheringId));
        return modelAndView;
    }
}
