package com.hana.onemoim.member.service;

import com.hana.onemoim.member.dto.SignupMemberDto;

import java.sql.Date;

public interface MemberService {
    // 아이디 중복 확인
    boolean isLoginIdExist(String loginId);

    // 회원가입
    void signupMember(SignupMemberDto signupMemberDto);

    // 성별 코드 분류
    int classifyGender(String personalIdNumber);
}
