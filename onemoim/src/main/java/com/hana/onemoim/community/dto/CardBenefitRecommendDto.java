package com.hana.onemoim.community.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CardBenefitRecommendDto {
    private int total; // 지출총액
    private String firstArea; // 첫번째 영역
    private int firstSum; // 첫번째 영역 지출총액
    private int firstPercent; // 첫번째 영역 비율
    private String secondArea; // 두번째 영역
    private int secondSum; // 두번쨰 영역 지출총액
    private int secondPercent; // 두번째 영역 비율
    private String thirdArea; // 세번쨰 영역
    private int thirdSum; // 세번쨰 영역 지출총액
    private int thirdPercent; // 세번쨰 영역 비율
    private int discountTotal; // 월 할인 예상액
}
