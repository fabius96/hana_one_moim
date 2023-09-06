package com.hana.onemoim.common.mapper;

import com.hana.onemoim.common.dto.InterestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InterestMapper {

    // 관심사 등록
    void insertInterest(InterestDto interestDto);

    // 특정 관심사를 가진 모임 ID 조회
    List<Integer>  selectGatheringIdFromInterest(String interest);
}
