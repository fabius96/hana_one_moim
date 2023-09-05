package com.hana.onemoim.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterestDto {
    private int memberId; // 회원ID
    private int gatheringId; // 모임ID
    private int interestId; // 관심사ID
    private String interestName; // 관심사명
}
