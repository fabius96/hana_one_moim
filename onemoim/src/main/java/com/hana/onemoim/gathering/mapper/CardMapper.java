package com.hana.onemoim.gathering.mapper;

import com.hana.onemoim.gathering.dto.GatheringCardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardMapper {

    // 모임카드생성
    void insertGatheringCard(GatheringCardDto gatheringCardDto);
}
