package com.hana.onemoim.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class MemberDto {
    private int memberId; // 회원ID
    private String loginId; // 로그인ID
    private int genderCode; // 성별코드
    private String name; // 이름
    private String email; // 이메일주소
    private String dateOfBirth; // 생년월일
    private String phoneNumber; // 연락처
    private Date createdAt; // created_at
    private Date modifiedAt; // modified_at
    private String password; // 비밀번호
    private String simplePassword; // 간편비밀번호
    private String personalIdNumber; // 주민등록번호
    private String zipCode; // 주소
    private String address; // 상세주소
    private String detailAddress; // 우편번호
}
