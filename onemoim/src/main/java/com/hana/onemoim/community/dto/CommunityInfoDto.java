package com.hana.onemoim.community.dto;

import com.hana.onemoim.gathering.dto.GatheringDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CommunityInfoDto {
    private GatheringDto gatheringDto;
    private int gatheringLeaderId;
    private List<GatheringMemberDto> gatheringMemberDtoList;
}
