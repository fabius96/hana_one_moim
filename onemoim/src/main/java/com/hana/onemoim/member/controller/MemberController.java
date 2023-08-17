package com.hana.onemoim.member.controller;

import com.hana.onemoim.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/signin")
    public ModelAndView showSignin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signin");
        return modelAndView;
    }

    @GetMapping(value = "/signup")
    public ModelAndView showSignup() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @GetMapping("/api/member/login_id/check")
    @ResponseBody
    public boolean checkLoginId(@RequestParam String loginId) {
        return memberService.isLoginIdExist(loginId);
    }
}
