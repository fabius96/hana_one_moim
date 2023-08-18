package com.hana.onemoim.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupMemberDto {
    private String loginId;
    private int genderCode;
    private String name;
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
