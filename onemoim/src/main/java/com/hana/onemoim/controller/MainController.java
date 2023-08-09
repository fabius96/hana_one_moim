package com.hana.onemoim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping(value = {"/before_login_main", "/"})
    public ModelAndView showBeforeLoginMain() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("before_login_main");
        return modelAndView;
    }

    @GetMapping(value = "/after_login_main")
    public ModelAndView showAfterLoginMain() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("after_login_main");
        return modelAndView;
    }
}
