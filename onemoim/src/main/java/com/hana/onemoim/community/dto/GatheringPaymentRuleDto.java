package com.hana.onemoim.community.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GatheringPaymentRuleDto {
    private int gatheringPaymentRuleId; // 모임회비규칙ID(SEQ.NEXTVAL)
    private int gatheringId; // 모임ID
    private int paymentCycleCode; // 납부주기코드
    private int paymentDay; // 납부일
    private String startDate; // 시작일
    private int paymentAmount; // 납부금액
}
