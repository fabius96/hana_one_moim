package com.hana.onemoim.transaction.mapper;

import com.hana.onemoim.transaction.dto.MemberTransactionDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {
    // 거래내역생성
    void insertTransaction(MemberTransactionDto memberTransactionDto);
}
