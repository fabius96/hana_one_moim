package com.bank.shinhan.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberTransactionDto {
    private int id;
    private String accountNumber;
    private String otherAccountNumber;
    private int transactionTypeCode;
    private int transactionAmount;
    private int balanceAfterTransaction;
    private String transactionTime;
    private String memo;

    @Builder
    public MemberTransactionDto(String accountNumber, String otherAccountNumber, int transactionTypeCode, int transactionAmount, int balanceAfterTransaction, String memo) {
        this.accountNumber = accountNumber;
        this.otherAccountNumber = otherAccountNumber;
        this.transactionTypeCode = transactionTypeCode;
        this.transactionAmount = transactionAmount;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.memo = memo;
    }
}
