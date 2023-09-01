package com.hana.onemoim.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/before_login_main", "/"})
    public String showBeforeLoginMain() {
        return "before_login_main";
    }

    @GetMapping("/after-login-main")
    public String showAfterLoginMain() {
        return "/after-login-main";
    }
}
