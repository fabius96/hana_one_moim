package com.hana.onemoim.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentTransferWrapper {
    private AccountTransferDto accountTransferDto; // 회비납부 및 입금하기 페이지 form 데이터
    private int gatheringId; // 모임ID
    private int gatheringMemberId; // 모임원ID
}
