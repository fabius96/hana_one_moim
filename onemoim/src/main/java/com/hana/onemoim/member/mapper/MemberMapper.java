package com.hana.onemoim.member.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    int countMemberByLoginId(String LoginId);
}
