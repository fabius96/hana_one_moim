package com.hana.onemoim.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class signupMemberDto {
    private String loginId;
    // ::TODO gender_code, 입력받은 personalIdNumber에 비즈니스 로직 추가 필요
//    gender_code    NUMBER NOT NULL,
    private String name;
    // ::TODO 입력 받은 후 형변환 필요
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String password;
    private String simplePassword;
    private String personalIdNumber;
    private String zipCode;
    private String address;
    private String detailAddress;
}
