package com.server.openbanking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
    private String accountNumber; // 계좌번호
    private int memberId; // 회원ID
    private int bankCode; // 은행코드
    private int accountStatusCode; // 계좌상태코드
    private int accountTypeCode; // 계좌종류코드
    private String accountPassword; // 비밀번호
    private int balance; // 잔액
    private String openbankingRegistered; // 오픈뱅킹등록여부
    private String accountNickname; // 계좌별칭
}
