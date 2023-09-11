package com.hana.onemoim.community.dto;

import com.hana.onemoim.gathering.dto.GatheringDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommunityMainDto {
    private GatheringDto gatheringDto;
    private String message;
    private boolean redirectRequired;
}
