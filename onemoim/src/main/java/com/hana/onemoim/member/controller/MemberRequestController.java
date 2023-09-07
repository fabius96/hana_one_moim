package com.hana.onemoim.member.controller;

import com.hana.onemoim.member.dto.MemberDto;
import com.hana.onemoim.member.dto.SignupMemberDto;
import com.hana.onemoim.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MemberRequestController {

    private final MemberService memberService;

    public MemberRequestController(MemberService memberService) {
        this.memberService = memberService;
    }


    // 로그인
    @PostMapping("/signin")
    public ModelAndView signin(String loginId, String memberPassword, HttpSession httpSession) {
        MemberDto memberDto = memberService.signin(loginId, memberPassword);
        ModelAndView modelAndView = new ModelAndView();

        if (memberDto != null) { // 로그인 성공 시
            httpSession.setAttribute("loggedInMember", memberDto);
            String destination = (String) httpSession.getAttribute("destination");

            if (destination != null) { // 1. 이전에 로그인하지 않은 상태로 접근했던 페이지가 존재할 경우
                httpSession.removeAttribute("destination");
                modelAndView.setViewName("redirect:" + destination);

            } else { // 2. 일반적인 순서로 로그인을 시도했을 경우
                modelAndView.setViewName("redirect:after-login-main");
            }

        } else { // 로그인 실패 시
            modelAndView.addObject("errorMessage", "아이디 혹은 패스워드가 일치하지 않습니다.");
            modelAndView.setViewName("/signin"); // 로그인 실패 시 다시 로그인 페이지로
        }
        return modelAndView;
    }

    // 회원가입
    @PostMapping("/signup")
    public ModelAndView registerMember(SignupMemberDto signupMemberDto) {
        ModelAndView modelAndView = new ModelAndView("/interest");
        int memberId = memberService.signupMember(signupMemberDto);
        modelAndView.addObject("memberId", memberId);
        return modelAndView;
    }

    //관심사 설정
    @PostMapping("/interest")
    public ModelAndView registerMemberInterest(@RequestParam int memberId,
                                               @RequestParam List<String> interestNames) {
        memberService.registerMemberInterest(memberId, interestNames);
        return new ModelAndView("/signup-ok");
    }

    // 로그아웃
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("redirect:after-login-main");
        httpSession.removeAttribute("loggedInMember");
        return modelAndView;
    }
}
