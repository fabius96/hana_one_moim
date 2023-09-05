package com.hana.onemoim.common.mapper;

import com.hana.onemoim.common.dto.InterestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterestMapper {

    // 관심사 등록
    void insertInterest(InterestDto interestDto);
}
