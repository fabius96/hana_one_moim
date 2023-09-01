package com.hana.onemoim.gathering.mapper;

import com.hana.onemoim.gathering.dto.CardBenefitDto;
import com.hana.onemoim.gathering.dto.GatheringCardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardMapper {

    // 모임카드생성
    void insertGatheringCard(GatheringCardDto gatheringCardDto);

    // gathering_card_id 가져오기
    int getNextCardSeq();

    // 카드혜택설정
    void insertCardBenefit(CardBenefitDto cardBenefitDto);
}
