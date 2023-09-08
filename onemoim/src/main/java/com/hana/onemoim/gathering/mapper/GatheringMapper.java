package com.hana.onemoim.gathering.mapper;

import com.hana.onemoim.gathering.dto.GatheringCreateDto;
import com.hana.onemoim.gathering.dto.GatheringDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    // 모임 검색
    List<GatheringDto> selectGatheringByKeyword(String keyword);

    // 모임 검색 결과 수
    int countGatheringByKeyword(String keyword);

    // 모임 분류 조회
    GatheringDto selectGatheringByGatheringId(@Param("onlyPublic") boolean onlyPublic,
                                              @Param("gatheringId") int gatheringId);

    // 모임 가입 여부 확인
    Boolean isMemberJoined(@Param("gatheringId") int gatheringId, @Param("memberId") int memberId);

    // 모임장 ID 확인
    int selectGatheringLeaderId(@Param("gatheringId") int gatheringId);
}
