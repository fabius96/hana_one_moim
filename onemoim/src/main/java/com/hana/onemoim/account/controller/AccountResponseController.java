package com.hana.onemoim.account.controller;

import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.account.dto.MemberTransactionDto;
import com.hana.onemoim.account.service.AccountService;
import com.hana.onemoim.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountResponseController {
    private final AccountService accountService;

    // 로그인 후 메인 페이지 조회
    @GetMapping("/account/after-login-main")
    public String showAfterLoginMain() {
        return "after-login-main";
    }

    // 계좌조회(하나은행) 페이지 조회(로그인 O)
    @GetMapping("/account/account-info-hana")
    public ModelAndView showAccountInfoHana(HttpSession httpSession, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        modelAndView.setViewName("account/account-info-hana");
        List<AccountDto> accountDtoList = accountService.findAllAccount(memberDto.getPersonalIdNumber());
        modelAndView.addObject("accounts", accountDtoList);
        return modelAndView;
    }

    // 금융상품 페이지 조회
    @GetMapping("/account/account-product-list")
    public String showAccountProductList() {
        return "/account/account-product-list";
    }// 금융상품

    // 금융상품 개설 페이지 조회(로그인 O)
    @GetMapping("/account/account-opening")
    public ModelAndView openAccount(HttpSession httpSession,
                                    HttpServletRequest httpServletRequest,
                                    @RequestParam String productName) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        modelAndView.addObject("productName", productName);
        modelAndView.setViewName("/account/account-opening");
        return modelAndView;
    }

    // 금융상품 개설 성공 페이지 조회(로그인 O)
    @GetMapping("/account/account-opening-ok")
    public ModelAndView showAccountOpeningOk(HttpSession httpSession                                             ) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            return modelAndView;
        }

        modelAndView.setViewName("/account/account-opening-ok");
        return modelAndView;
    }

    // 계좌이체(하나은행) 페이지 조회(로그인 O)
    @GetMapping("/account/account-transfer-hana")
    public ModelAndView showAccountTransferHana(HttpSession httpSession,
                                                HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        List<AccountDto> accountDtoList = accountService.findAllAccount(memberDto.getPersonalIdNumber());
        modelAndView.addObject("accounts", accountDtoList);
        modelAndView.setViewName("/account/account-transfer-hana");

        return modelAndView;
    }

    // 계좌이체 완료 페이지 조회(로그인 O)
    @GetMapping("/account/account-transfer-ok")
    public ModelAndView showAccountTransferOk(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            return modelAndView;
        }

        modelAndView.setViewName("/account/account-transfer-ok");
        return modelAndView;
    }

    // 거래내역 조회 페이지 조회(로그인 O)
    @GetMapping("/account/account-transaction")
    public ModelAndView showAccountTransaction(HttpSession httpSession,
                                               HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }

        List<AccountDto> accountDtoList = accountService.findAllAccount(memberDto.getPersonalIdNumber());
        modelAndView.addObject("accounts", accountDtoList);
        modelAndView.setViewName("/account/account-transaction");
        return modelAndView;
    }

    // 계좌이체 내역 조회 메서드
    @GetMapping("/api/account/get-account-transaction")
    @ResponseBody
    public List<MemberTransactionDto> getAccountTransaction(AccountDto accountDto) {
        List<MemberTransactionDto> memberTransactionDtoList =
                accountService.findTransactionByAccountNumber(accountDto);
        for (MemberTransactionDto dto : memberTransactionDtoList) {
            System.out.println(dto.getTransactionTypeCode());
        }
        return memberTransactionDtoList;
    }
}
