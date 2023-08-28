package com.hana.onemoim.transaction.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class MemberTransactionDto {
    private int id;
    private String accountNumber;
    private String otherAccountNumber;
    private int transactionTypeCode;
    private int transactionAmount;
    private int balanceAfterTransaction;
    private Date transactionTime;
    private String memo;
}
