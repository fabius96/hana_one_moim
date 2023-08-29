package com.hana.onemoim.account.mapper;

import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.account.dto.MemberTransactionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper {
    // 거래내역생성
    void insertTransaction(MemberTransactionDto memberTransactionDto);

    // 거래내역 조회
    List<MemberTransactionDto> selectTransactionByAccountNumber(AccountDto accountDto);
}
