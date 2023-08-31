package com.hana.onemoim.gathering.service;

import com.hana.onemoim.gathering.dto.GatheringCreateDto;
import com.hana.onemoim.gathering.mapper.GatheringMapper;
import org.springframework.stereotype.Service;

@Service
public class GatheringServiceImpl implements GatheringService {

    private final GatheringMapper gatheringMapper;

    public GatheringServiceImpl(GatheringMapper gatheringMapper) {
        this.gatheringMapper = gatheringMapper;
    }

    // 모임 개설
    @Override
    public void createrGathering(GatheringCreateDto gatheringCreateDto) {
        gatheringCreateDto.setGatheringId(gatheringMapper.getNextSeq()+1); // 모임 ID 확인 및 설정
        gatheringMapper.insertGathering(gatheringCreateDto); // 모임 개설
        gatheringMapper.insertGatheringPaymentRule(gatheringCreateDto); // 모임회비규칙 생성
    }
}
