package com.hana.onemoim.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberTransactionDto {
    private int id;
    private String accountNumber;
    private String otherAccountNumber;
    private int transactionTypeCode;
    private int transactionAmount;
    private int balanceAfterTransaction;
    private String transactionTime;
    private String memo;
}
