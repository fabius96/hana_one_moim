package com.hana.onemoim.member.controller;

import com.hana.onemoim.member.dto.MemberDto;
import com.hana.onemoim.member.dto.SignupMemberDto;
import com.hana.onemoim.member.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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

    // 로그인
    @PostMapping(value = "/signin")
    public ResponseEntity<Map<String, String>> signin(String loginId, String memberPassword, HttpSession httpSession) {
        MemberDto memberDto = memberServiceImpl.signin(loginId, memberPassword);

        Map<String, String> response = new HashMap<>();

        if (memberDto != null) {
            httpSession.setAttribute("loggedInMember", memberDto);
            response.put("status", "success");
        } else {
            response.put("status", "error");
            response.put("message", "아이디 혹은 패스워드가 일치하지 않습니다.");
        }

        return ResponseEntity.ok(response);
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
