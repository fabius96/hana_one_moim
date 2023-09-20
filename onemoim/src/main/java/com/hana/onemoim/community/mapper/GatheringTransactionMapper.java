package com.hana.onemoim.community.mapper;

import com.hana.onemoim.account.dto.MemberTransactionDto;
import com.hana.onemoim.community.dto.GatheringTransactionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GatheringTransactionMapper {

    // 모임계좌 거래내역 조회
    List<GatheringTransactionDto> selectTransactionByAccountNumber(String accountNumber);

    // 지출분석용 데이터 조회
    List<GatheringTransactionDto> selectTransactionForCard(String accountNumber);

    // 모임 거래내역 생성
    void insertGatheringTransaction(MemberTransactionDto memberTransactionDto);
}
