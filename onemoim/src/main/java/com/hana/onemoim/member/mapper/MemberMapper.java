package com.hana.onemoim.member.mapper;

import com.hana.onemoim.member.dto.MemberDto;
import com.hana.onemoim.member.dto.SignupMemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    // 아이디 중복 체크
    int countMemberByLoginId(String loginId);

    // 회원가입
    void insertMember(SignupMemberDto signupMemberDto);

    // 로그인
    MemberDto findMemberByLoginIdAndPassword(@Param("loginId") String loginId, @Param("password") String password);

    // 모임장 이름 조회
    String selectNameByLeaderId(int gatheringLeaderId);

    // member_id 가져오기
    int getNextMemberSeq();
}
