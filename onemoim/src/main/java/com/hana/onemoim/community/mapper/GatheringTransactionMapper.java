package com.hana.onemoim.community.mapper;

import com.hana.onemoim.account.dto.MemberTransactionDto;
import com.hana.onemoim.community.dto.GatheringTransactionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GatheringTransactionMapper {

    // 모임계좌 거래내역 조회
    List<GatheringTransactionDto> selectTransactionByAccountNumber(@Param("accountNumber") String accountNumber,
                                                                   @Param("month") int month);

    // 지출분석용 데이터 조회
    List<GatheringTransactionDto> selectTransactionForCard(@Param("accountNumber") String accountNumber,
                                                           @Param("month") int month);

    // 모임거래내역 지출 TOP 5 조회
    List<GatheringTransactionDto> selectTransactionTop5ByAccountNumber(@Param("accountNumber") String accountNumber,
                                                                       @Param("month") int month);

    // 모임 거래내역 생성
    void insertGatheringTransaction(MemberTransactionDto memberTransactionDto);
}
