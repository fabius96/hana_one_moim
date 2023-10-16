package com.hana.onemoim.member.service;

import com.hana.onemoim.common.dto.InterestDto;
import com.hana.onemoim.common.mapper.InterestMapper;
import com.hana.onemoim.member.dto.MemberDto;
import com.hana.onemoim.member.dto.SignupMemberDto;
import com.hana.onemoim.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final InterestMapper interestMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public boolean isLoginIdExist(String loginId) {
        return memberMapper.countMemberByLoginId(loginId) > 0;
    }

    // 회원가입
    public int signupMember(SignupMemberDto signupMemberDto) {
        int memberId = memberMapper.getNextMemberSeq()+1;

        // 성별분류
        signupMemberDto.setGenderCode(classifyGender(signupMemberDto.getPersonalIdNumber()));

        // 비밀번호 및 주민등록번호 해싱
        signupMemberDto.setPassword(passwordEncoder.encode(signupMemberDto.getPassword()));
        signupMemberDto.setPersonalIdNumber(passwordEncoder.encode(signupMemberDto.getPersonalIdNumber()));

        memberMapper.insertMember(signupMemberDto);
        return memberId;
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
    public MemberDto signin(String loginId, String password) {
        MemberDto memberDto = memberMapper.findMemberByLoginId(loginId);
        if (memberDto != null && passwordEncoder.matches(password, memberDto.getPassword())) {
            return memberDto;
        }
        return null;
    }

    // 회원 관심사 등록
    @Override
    public void registerMemberInterest(int memberId, List<String> interestNames) {
        System.out.println("MemberServiceImpl.registerMemberInterest");
        for(String interestName : interestNames){
            InterestDto interestDto = new InterestDto();
            interestDto.setMemberId(memberId);
            interestDto.setInterestName(interestName);
            interestMapper.insertInterest(interestDto);
        }
    }
}
