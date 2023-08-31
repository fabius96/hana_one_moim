package com.hana.onemoim.gathering.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class GatheringCreateDto {

    private int gatheringId; // 모임ID
    private int gatheringLeaderId; // 모임장ID
    private String gatheringName; //모임명
    private String gatheringDescription; // 모임설명
    private int paymentAmount; // 모임 회비
    private int paymentDay; // 납부일
    private Date startDate; //모임 회비 납부 시작일
    private int paymentCycleCode; // 납부 주기
    private int memberId; // 회원ID
    private String memberName; // 회원명
}

