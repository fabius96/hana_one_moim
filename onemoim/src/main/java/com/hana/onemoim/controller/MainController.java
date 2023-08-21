package com.hana.onemoim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping({"/before_login_main", "/"})
    public String showBeforeLoginMain() {
        return "before_login_main";
    }

    @GetMapping("/after_login_main")
    public String showAfterLoginMain() {
        return "after_login_main";
    }
}
