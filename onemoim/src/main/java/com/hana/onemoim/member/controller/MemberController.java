package com.hana.onemoim.member.controller;

import com.hana.onemoim.member.dto.MemberDto;
import com.hana.onemoim.member.dto.SignupMemberDto;
import com.hana.onemoim.member.service.MemberService;
import com.hana.onemoim.member.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping( "/signin")
    public String showSignin() {
        return "signin";
    }

    // 로그인
    @PostMapping("/signin")
    public ModelAndView signin(String loginId, String memberPassword, HttpSession httpSession) {
        MemberDto memberDto = memberService.signin(loginId, memberPassword);
        System.out.println(memberDto);
        ModelAndView modelAndView = new ModelAndView();

        if (memberDto != null) {
            httpSession.setAttribute("loggedInMember", memberDto);
            modelAndView.setViewName("after_login_main");
        } else {
            modelAndView.addObject("errorMessage", "아이디 혹은 패스워드가 일치하지 않습니다.");
            modelAndView.setViewName("signin"); // 로그인 실패 시 다시 로그인 페이지로
        }
        return modelAndView;
    }

    @GetMapping("/signup")
    public String showSignup() {
        return "signup";
    }

    // 회원가입
    @PostMapping("/signup")
    public String registerMember(SignupMemberDto signupMemberDto) {
        memberService.signupMember(signupMemberDto);
        return "redirect:/signin?signupSuccess=true";
    }

    // 아이디 중복 확인
    @GetMapping("/api/member/login_id/check")
    @ResponseBody
    public boolean checkLoginId(@RequestParam String loginId) {
        return memberService.isLoginIdExist(loginId);
    }

    // 로그아웃
    @GetMapping("/api/member/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("loggedInMember");
        return "redirect:/signin";
    }
}
