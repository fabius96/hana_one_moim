package com.hana.onemoim.member.controller;

import com.hana.onemoim.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberResponseController {

    private final MemberService memberService;

    public MemberResponseController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 로그인 페이지 조회
    @GetMapping("/signin")
    public String showSignin() {
        return "signin";
    }

    // 회원가입 페이지 조회
    @GetMapping("/signup")
    public String showSignup() {
        return "signup";
    }

    // 관심사 설정 페이지 조회
    @GetMapping("/interest")
    public ModelAndView getMemberInterest() {
        return new ModelAndView("/interest");
    }

    // 회원가입 완료 페이지 조회
    @GetMapping("/signup-ok")
    public String showSignupOk() {
        return "signup-ok";
    }

    // 아이디 중복 확인
    @GetMapping("/api/member/login_id/check")
    @ResponseBody
    public boolean checkLoginId(@RequestParam String loginId) {
        return memberService.isLoginIdExist(loginId);
    }
}
