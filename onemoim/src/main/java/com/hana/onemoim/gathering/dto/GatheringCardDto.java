package com.hana.onemoim.gathering.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class GatheringCardDto {

    private int cardId; // 카드ID
    private String accountNumber; // 계좌번호
    private String cardName; // 카드명
    private String cardNumber; // 카드번호
    private String cardStatusCode; // 카드상태코드
    private Date expirationDate; // 만료일

    @Builder
    public GatheringCardDto(int cardId, String accountNumber, String cardName, String cardNumber) {
        this.cardId = cardId;
        this.accountNumber = accountNumber;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
    }
}
