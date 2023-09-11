package com.hana.onemoim.community.mapper;

import com.hana.onemoim.community.dto.GatheringMemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GatheringMemberMapper {

    // gathering_id 가져오기
    List<GatheringMemberDto> selectGatheringMemberByGatheringId(int gatheringId);

    // 모임원 상태 코드 확인
    int isMemberStatusCodeActive(@Param("gatheringId") int gatheringId,
                                 @Param("memberId") int memberId);

    // 모임원 상태 코드 변경
    void updateMemberStatusCode(@Param("memberStatusCode") int memberStatusCode,
                                @Param("memberId") int memberId);
}
