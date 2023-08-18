package com.hana.onemoim.member.controller;

import com.hana.onemoim.member.dto.SignupMemberDto;
import com.hana.onemoim.member.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;

    @Autowired
    public MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
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

    // 회원가입
    @PostMapping("/signup")
    public String registerMember(SignupMemberDto signupMemberDto) {
        memberServiceImpl.signupMember(signupMemberDto);
        return "redirect:/signin?signupSuccess=true";
    }


    // 아이디 중복 확인
    @GetMapping("/api/member/login_id/check")
    @ResponseBody
    public boolean checkLoginId(@RequestParam String loginId) {
        return memberServiceImpl.isLoginIdExist(loginId);
    }
}
