package com.hana.onemoim.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GatheringAccountDto {
    private String accountNumber; // 계좌번호
    private int gatheringId; // 모임ID
    private int bankCode; // 은행코드
    private int accountStatusCode; // 계좌상태코드
    private int accountTypeCode; // 계좌종류코드
    private String accountPassword; // 비밀번호
    private int balance; // 잔액
    private String accountNickname; // 계좌별칭

    @Builder
    public GatheringAccountDto(String accountNumber,
                               int gatheringId,
                               String accountPassword,
                               String accountNickname) {
        this.accountNumber = accountNumber;
        this.gatheringId = gatheringId;
        this.accountPassword = accountPassword;
        this.accountNickname = accountNickname;
    }
}
