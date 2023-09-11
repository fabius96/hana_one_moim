package com.hana.onemoim.community.service;

import com.hana.onemoim.community.dto.CommunityInfoDto;
import com.hana.onemoim.community.dto.CommunityMainDto;
import com.hana.onemoim.community.dto.GatheringMemberDto;
import com.hana.onemoim.community.mapper.GatheringMemberMapper;
import com.hana.onemoim.gathering.dto.GatheringDto;
import com.hana.onemoim.gathering.mapper.GatheringMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final GatheringMemberMapper gatheringMemberMapper;
    private final GatheringMapper gatheringMapper;

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

    // 커뮤니티 정보 조회(메인페이지 접근용)
    @Override
    public CommunityMainDto searchCommunityInfo(int gatheringId, int memberId) {
        int memberStatusCode = checkMemberStatusCode(gatheringId, memberId);

        if (memberStatusCode == 71) { // 활동 정지 상태의 모임원이 모임에 접근할 경우
            return CommunityMainDto.builder()
                    .gatheringDto(null)
                    .message("활동이 정지된 모임원입니다. <br/> 모임장에게 문의하세요")
                    .redirectRequired(true)
                    .build();
        } else if (memberStatusCode == 72) { // 승인 대기 상태의 모임원이 모임에 접근할 경우
            return CommunityMainDto.builder()
                    .gatheringDto(null)
                    .message("승인 대기 상태의 모임원입니다.")
                    .redirectRequired(true)
                    .build();
        }

        GatheringDto gatheringDto = gatheringMapper.selectGatheringByGatheringId(false, gatheringId);
        return CommunityMainDto.builder()
                .gatheringDto(gatheringDto)
                .message(null)
                .redirectRequired(false)
                .build();
    }

    // 개별 커뮤니티 정보 조회
    @Override
    public CommunityInfoDto getCommunityInfo(int gatheringId) {
        return CommunityInfoDto.builder()
                .gatheringDto(gatheringMapper.selectGatheringByGatheringId(false, gatheringId))
                .gatheringLeaderId(gatheringMapper.selectGatheringLeaderId(gatheringId))
                .gatheringMemberDtoList(findGatheringMemberByGatheringId(gatheringId))
                .build();
    }

    // 모임원 상태 코드 변경
    @Override
    public void updateMemberStatusCode(int memberStatusCode, int memberId) {
        gatheringMemberMapper.updateMemberStatusCode(memberStatusCode, memberId);
    }
}
