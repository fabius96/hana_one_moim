package com.hana.onemoim.gathering.mapper;

import com.hana.onemoim.gathering.dto.GatheringCreateDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GatheringMapper {

    // 모임 개설
    void insertGathering(GatheringCreateDto gatheringCreateDto);
}
