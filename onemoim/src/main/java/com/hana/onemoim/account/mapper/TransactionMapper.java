package com.hana.onemoim.account.mapper;

import com.hana.onemoim.account.dto.MemberTransactionDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {
    // 거래내역생성
    void insertTransaction(MemberTransactionDto memberTransactionDto);
}
