package com.hana.onemoim.account.controller;

import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.account.dto.AccountTransferDto;
import com.hana.onemoim.account.dto.MemberTransactionDto;
import com.hana.onemoim.account.service.AccountService;
import com.hana.onemoim.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;


    // 로그인 후 메인
    @GetMapping("/account/after-login-main")
    public String showAfterLoginMain() {
        return "after-login-main";
    }

    // 계좌조회 - 하나은행
    @GetMapping("/account/account-info-hana")
    public ModelAndView showAccountInfoHana(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            return modelAndView;
        }

        modelAndView.setViewName("account/account-info-hana");
        List<AccountDto> accountDtoList = accountService.findAllAccount(memberDto.getPersonalIdNumber());
        modelAndView.addObject("accounts", accountDtoList);
        return modelAndView;
    }

    // 금융상품
    @GetMapping("/account/account-product-list")
    public String showAccountProductList() {
        return "/account/account-product-list";
    }// 금융상품

    // 금융상품 개설페이지
    @GetMapping("/account_opening")
    public ModelAndView openAccount(@RequestParam String productName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productName", productName);
        modelAndView.setViewName("account_opening");
        return modelAndView;
    }

    // 금융상품 개설
    @PostMapping("/account_opening")
    public String makeAccount(@RequestParam int memberId,
                              @RequestParam String simplePassword,
                              @RequestParam String accountNickname) {
        accountService.openAccount(memberId, simplePassword, accountNickname);
        return "/account/account-opening-ok";
    }

    // 금융상품 개설 성공
    @GetMapping("/account/account-opening-ok")
    public String showAccountOpeningOk() {
        return "/account/account-opening-ok";
    }

    // 계좌이체(하나은행) 페이지 조회
    @GetMapping("/account/account-transfer-hana")
    public ModelAndView showAccountTransferHana(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto != null) {
            List<AccountDto> accountDtoList = accountService.findAllAccount(memberDto.getPersonalIdNumber());
            modelAndView.addObject("accounts", accountDtoList);
            modelAndView.setViewName("account/account-transfer-hana");
        }

        return modelAndView;
    }

    // 계좌이체
    @PostMapping("/account/account-transfer-hana")
    public String accountTransfer(@ModelAttribute AccountTransferDto accountTransferDto){
        accountService.accountTransfer(accountTransferDto);
        return "/account/account-transfer-ok";
    }

    // 계좌이체 완료 페이지 조회
    @GetMapping("/account/account-transfer-ok")
    public String showAccountTransferOk(){
        return "/account/account-transfer-ok";
    }

    // 거래내역 조회 페이지 조회
    @GetMapping("/account/account-transaction")
    public ModelAndView showAccountTransaction(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto != null) {
            List<AccountDto> accountDtoList = accountService.findAllAccount(memberDto.getPersonalIdNumber());
            modelAndView.addObject("accounts", accountDtoList);
            modelAndView.setViewName("account/account-transaction");
        }

        return modelAndView;
    }

    // 계좌이체 내역 조회
    @GetMapping("/api/account/get-account-transaction")
    @ResponseBody
    public List<MemberTransactionDto> getAccountTransaction(AccountDto accountDto){
        System.out.println(accountDto.getAccountNumber());
        List<MemberTransactionDto> memberTransactionDtoList =
                accountService.findTransactionByAccountNumber(accountDto);
        for(MemberTransactionDto dto : memberTransactionDtoList){
            System.out.println(dto.getTransactionTypeCode());
        }
        return memberTransactionDtoList;
    }
}
