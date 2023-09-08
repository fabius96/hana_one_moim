package com.hana.onemoim.community.service;

import com.hana.onemoim.community.dto.GatheringMemberDto;
import com.hana.onemoim.community.mapper.GatheringMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final GatheringMemberMapper gatheringMemberMapper;

    // 모임원 찾기
    @Override
    public List<GatheringMemberDto> findGatheringMemberByGatheringId(int gatheringId) {
        return gatheringMemberMapper.selectGatheringMemberByGatheringId(gatheringId);
    }

    // 모임원 상태 코드 확인
    @Override
    public int checkMemberStatusCode(int gatheringId, int memberId) {
        return gatheringMemberMapper.isMemberStatusCodeActive(gatheringId, memberId);
    }
}
