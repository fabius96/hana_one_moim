package com.hana.onemoim.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountTransferDto {
    private String accountNumber; // 계좌번호
    private String accountPassword; // 비밀번호
    private int amount; // 입금금액
    private String otherAccountNumber; // 상대계좌번호
    private String memo; // 메모;
    private int balanceAfterTransaction;
}
