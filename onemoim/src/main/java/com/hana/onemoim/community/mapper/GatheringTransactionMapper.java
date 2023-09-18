package com.hana.onemoim.community.mapper;

import com.hana.onemoim.community.dto.GatheringTransactionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GatheringTransactionMapper {

    // 모임계좌 거래내역 조회
    List<GatheringTransactionDto> selectTransactionByAccountNumber(String accountNumber);
}
