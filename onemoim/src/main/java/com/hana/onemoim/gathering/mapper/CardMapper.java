package com.hana.onemoim.gathering.mapper;

import com.hana.onemoim.gathering.dto.CardBenefitDto;
import com.hana.onemoim.gathering.dto.GatheringCardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardMapper {

    // 모임카드생성
    void insertGatheringCard(GatheringCardDto gatheringCardDto);

    // gathering_card_id 가져오기
    int getNextCardSeq();

    // 카드혜택설정
    void insertCardBenefit(CardBenefitDto cardBenefitDto);

    // 모임 카드 혜택 변경
    void updateCardBenefit(CardBenefitDto cardBenefitDto);

    // 카드혜택조회
    List<CardBenefitDto> selectCardBenefitByGatheringId(int gatheringId);

    // 혜택명 및 설명 조회
    CardBenefitDto selectBenefit(int benefitId);
}
