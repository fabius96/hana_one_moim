package com.hana.onemoim.community.service;

import com.hana.onemoim.community.dto.GatheringMemberDto;

import java.util.List;

public interface CommunityService {

    // 모임원 조회
    List<GatheringMemberDto> findGatheringMemberByGatheringId(int gatheringId);
}
