package com.hana.onemoim.gathering.mapper;

import com.hana.onemoim.gathering.dto.GatheringCreateDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GatheringMapper {

    // gathering_id 가져오기
    int getNextSeq();

    // 모임 개설
    void insertGathering(GatheringCreateDto gatheringCreateDto);

    // 모임회비규칙 생성
    void insertGatheringPaymentRule(GatheringCreateDto gatheringCreateDto);
}
