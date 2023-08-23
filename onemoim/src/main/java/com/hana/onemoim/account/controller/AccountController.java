package com.hana.onemoim.account.controller;

import com.hana.onemoim.account.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // 로그인 후 메인
    @GetMapping("/account/after-login-main")
        public String showAfterLoginMain(){
        return "after-login-main";
    }

    // 계좌조회 - 하나은행
    @GetMapping("/account/account-info-hana")
    public String showAccountInfoHana() {
        return "/account/account-info-hana";
    }

    // 금융상품
    @GetMapping("/account_product_list")
    public String showAccountProductList() {
        return "account_product_list";
    }// 금융상품

    // 금융상품 개설페이지
    @GetMapping("/account_opening")
    public ModelAndView openAccount(@RequestParam String productname) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productName", productname);
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
    public String showAccountOpeningOk(){
        return "/account/account-opening-ok";
    }
}
