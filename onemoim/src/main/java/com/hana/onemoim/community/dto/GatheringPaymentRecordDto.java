package com.hana.onemoim.community.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GatheringPaymentRecordDto {
    private int gatheringPaymentRecordId; // 모임회비납부기록ID(SEQ.NEXTVAL)
    private int gatheringId; // 모임ID
    private int gatheringMemberId; // 모임원ID
    private int gatheringPaymentRuleId; // 모임회비규칙ID
    private int paymentAmount; // 납부금액
    private String paymentDay; // 납부일
    private String paymentDueDate; // 회비납부기한
    private int round; // 회차

    @Builder
    public GatheringPaymentRecordDto(int gatheringId, int gatheringMemberId, int gatheringPaymentRuleId, int paymentAmount, String paymentDueDate, int round) {
        this.gatheringId = gatheringId;
        this.gatheringMemberId = gatheringMemberId;
        this.gatheringPaymentRuleId = gatheringPaymentRuleId;
        this.paymentAmount = paymentAmount;
        this.paymentDueDate = paymentDueDate;
        this.round = round;
    }
}
