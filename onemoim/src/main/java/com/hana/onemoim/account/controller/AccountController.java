package com.hana.onemoim.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    // 계좌조회 - 하나은행
    @GetMapping("/account_info_hana")
    public String showAccountInfoHana() {
        return "account_info_hana";
    }

    // 금융상품
    @GetMapping("/account_product_list")
    public String showAccountProductList(){
        return "account_product_list";
    }
}
