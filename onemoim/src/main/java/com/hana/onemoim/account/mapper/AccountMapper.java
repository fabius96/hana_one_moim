package com.hana.onemoim.account.mapper;

import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.account.dto.AccountTransferDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    // 계좌개설
    void insertAccount(AccountDto accountDto);

    // 전체계좌조회
    List<AccountDto> selectAccountByPersonalIdNumber(String personalIdNumber);

    // 출금
    void updateAccountBalance(AccountTransferDto accountTransferDto);

    void updateAccountBalanceDeposit(AccountTransferDto accountTransferDto);

    // 잔액조회
    int selectBalance(AccountTransferDto accountTransferDto);
}
