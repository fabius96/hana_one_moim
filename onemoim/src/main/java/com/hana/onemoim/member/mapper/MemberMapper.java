package com.hana.onemoim.member.mapper;

import com.hana.onemoim.member.dto.SignupMemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    // 아이디 중복 체크
    int countMemberByLoginId(String LoginId);

    // 회원가입
    void insertMember(SignupMemberDto signupMemberDto);
}
