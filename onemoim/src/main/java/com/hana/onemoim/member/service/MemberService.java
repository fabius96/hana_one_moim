package com.hana.onemoim.member.service;

import com.hana.onemoim.member.dto.MemberDto;
import com.hana.onemoim.member.dto.SignupMemberDto;

import java.util.List;

public interface MemberService {
    // 아이디 중복 확인
    boolean isLoginIdExist(String loginId);

    // 회원가입
    int signupMember(SignupMemberDto signupMemberDto);

    // 성별 코드 분류
    int classifyGender(String personalIdNumber);

    // 로그인
    MemberDto signin(String loginId, String password);

    // 회원 관심사 설정
    void registerMemberInterest(int memberId, List<String> interestNames);
}
