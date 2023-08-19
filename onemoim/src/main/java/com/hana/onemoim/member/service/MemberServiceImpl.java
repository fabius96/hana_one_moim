package com.hana.onemoim.member.service;

import com.hana.onemoim.member.dto.MemberDto;
import com.hana.onemoim.member.dto.SignupMemberDto;
import com.hana.onemoim.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public boolean isLoginIdExist(String loginId) {
        return memberMapper.countMemberByLoginId(loginId) > 0;
    }

    // 회원가입
    public void signupMember(SignupMemberDto signupMemberDto) {
        // 성별분류
        signupMemberDto.setGenderCode(classifyGender(signupMemberDto.getPersonalIdNumber()));
        memberMapper.insertMember(signupMemberDto);
    }

    // 성별분류
    public int classifyGender(String personalIdNumber) {
        int genderNumber = Character.getNumericValue(personalIdNumber.charAt(0));
        if (genderNumber == 1 || genderNumber == 3) {
            return 10; // 남자
        } else {
            return 11; // 여자
        }
    }

    // 로그인
    public MemberDto signin(String loginId, String password){
        MemberDto memberDto = memberMapper.findMemberByLoginIdAndPassword(loginId, password);
        return memberDto;
    }
}
