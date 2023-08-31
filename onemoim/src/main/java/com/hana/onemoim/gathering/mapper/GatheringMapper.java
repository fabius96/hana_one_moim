package com.hana.onemoim.gathering.mapper;

import com.hana.onemoim.gathering.dto.GatheringCreateDto;
import com.hana.onemoim.gathering.dto.GatheringDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GatheringMapper {

    // gathering_id 가져오기
    int getNextSeq();

    // 모임 개설
    void insertGathering(GatheringCreateDto gatheringCreateDto);

    // 모임회비규칙 생성
    void insertGatheringPaymentRule(GatheringCreateDto gatheringCreateDto);

    // 모임원 가입
    void insertGatheringMember(GatheringCreateDto gatheringCreateDto);

    // 가입한 모임 조회
    List<GatheringDto> selectGroupByMemberId(int memberId);

    // 모임대표이미지 조회
    String selectGatheringCoverImage(int gatheringId);
}
