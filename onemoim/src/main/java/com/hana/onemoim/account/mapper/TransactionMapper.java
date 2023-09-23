package com.hana.onemoim.account.mapper;

import com.hana.onemoim.account.dto.MemberTransactionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransactionMapper {
    // 거래내역생성
    void insertTransaction(MemberTransactionDto memberTransactionDto);

    // 거래내역 조회
    List<MemberTransactionDto> selectTransactionByAccountNumber(@Param("accountNumber") String accountNumber,
                                                                @Param("month") int month);
}
