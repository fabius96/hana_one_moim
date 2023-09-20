package com.hana.onemoim.gathering.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardBenefitDto {

    private int cardBenefitId; // 카드 혜택 ID
    private int cardId; // 카드 ID
    private int benefitId; // 혜택 ID
    private int benefitRate; // 할인율
    private String benefitName; // 혜택명
    private String benefitDescription; // 혜택설명
}
