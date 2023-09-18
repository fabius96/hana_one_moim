package com.hana.onemoim.community.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GatheringTransactionDto {
    private int transactionIdentificationNumber; // 거래식별번호(PK, SEQ)
    private String accountNumber; // 계좌번호
    private String otherAccountNumber; // 상대계좌번호
    private int transactionTypeCode; // 거래유형코드
    private int transactionCategoryCode; // 거래영역코드
    private int transactionAmount; // 거래금액
    private int balanceAfterTransaction; // 거래후잔액
    private String transactionTime; // 거래시각
    private String memo; // 거래메모
}
