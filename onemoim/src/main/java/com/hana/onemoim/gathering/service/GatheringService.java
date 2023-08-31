package com.hana.onemoim.gathering.service;

import com.hana.onemoim.gathering.dto.GatheringCreateDto;

public interface GatheringService {

    // 모임 개설
    void createrGathering(GatheringCreateDto gatheringCreateDto);
}
