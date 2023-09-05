package com.hana.onemoim.member.controller;

import com.hana.onemoim.member.dto.MemberDto;
import com.hana.onemoim.member.dto.SignupMemberDto;
import com.hana.onemoim.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/signin")
    public String showSignin() {
        return "signin";
    }

    // 로그인
    @PostMapping("/signin")
    public ModelAndView signin(String loginId, String memberPassword, HttpSession httpSession) {
        MemberDto memberDto = memberService.signin(loginId, memberPassword);
        ModelAndView modelAndView = new ModelAndView();
        httpSession.getAttribute("destination");

        if (memberDto != null) {
            httpSession.setAttribute("loggedInMember", memberDto);
            modelAndView.setViewName("after-login-main");
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
    public ModelAndView registerMember(SignupMemberDto signupMemberDto) {
        ModelAndView modelAndView = new ModelAndView("/interest");
        int memberId = memberService.signupMember(signupMemberDto);
        modelAndView.addObject("memberId", memberId);
        return modelAndView;
    }

     //관심사 설정
    @PostMapping("/interest")
    public ModelAndView registerMemberInterest(@RequestParam int memberId,
                                               @RequestParam List<String> interestNames){
        memberService.registerMemberInterest(memberId, interestNames);
     return new ModelAndView("/signup-ok");
    }

    // 관심사 설정 페이지 조회
    @GetMapping("/interest")
    public ModelAndView getMemberInterest() {
        return new ModelAndView("/interest");
    }

    // 회원가입 완료 페이지
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

    // 로그아웃
    @GetMapping("/api/member/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("loggedInMember");
        return "redirect:/signin";
    }
}
