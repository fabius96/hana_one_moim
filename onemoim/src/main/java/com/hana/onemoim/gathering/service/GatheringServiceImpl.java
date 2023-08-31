package com.hana.onemoim.gathering.service;

import com.hana.onemoim.gathering.dto.GatheringCreateDto;
import com.hana.onemoim.gathering.mapper.GatheringMapper;
import org.springframework.stereotype.Service;

@Service
public class GatheringServiceImpl implements GatheringService {

    private GatheringMapper gatheringMapper;

    public GatheringServiceImpl(GatheringMapper gatheringMapper) {
        this.gatheringMapper = gatheringMapper;
    }

    // 모임 개설
    @Override
    public void createrGathering(GatheringCreateDto gatheringCreateDto) {
        gatheringMapper.insertGathering(gatheringCreateDto);
    }
}
