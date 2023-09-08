package com.hana.onemoim.community.mapper;

import com.hana.onemoim.community.dto.GatheringMemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GatheringMemberMapper {

    // gathering_id 가져오기
    List<GatheringMemberDto> selectGatheringMemberByGatheringId(int gatheringId);
}
