package com.hana.onemoim.member.service;

import com.hana.onemoim.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public boolean isLoginIdExist(String loginId) {
        return memberMapper.countMemberByLoginId(loginId) > 0;
    }
}
