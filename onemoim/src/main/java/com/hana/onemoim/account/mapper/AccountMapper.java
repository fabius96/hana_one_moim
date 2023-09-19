package com.hana.onemoim.account.mapper;

import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.account.dto.AccountTransferDto;
import com.hana.onemoim.account.dto.GatheringAccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    // 계좌개설
    void insertAccount(AccountDto accountDto);

    // 모임계좌개설
    void insertGatheringAccount(GatheringAccountDto gatheringAccountDto);

    // 전체계좌조회
    List<AccountDto> selectAccountByPersonalIdNumber(String personalIdNumber);

    // 출금
    void updateAccountBalance(AccountTransferDto accountTransferDto);

    // 모임계좌 출금
    void updateGatheringAccountBalance(AccountTransferDto accountTransferDto);

    // 입금
    void updateAccountBalanceDeposit(AccountTransferDto accountTransferDto);

    // 입금(모임회비납부)
    void updateGatheringAccountBalanceDeposit(AccountTransferDto accountTransferDto);

    // 잔액조회
    int selectBalance(AccountTransferDto accountTransferDto);

    // 잔액조회
    int selectGatheringBalance(int gatheringId);

    // 잔액조회
    int selectBalanceForGatheringAccountWithdrawal(AccountTransferDto accountTransferDto);

    // 모임계좌 잔액조회
    int selectGatheringAccountBalance(AccountTransferDto accountTransferDto);

    // 모임계좌번호조회
    String selectAccountNumberByGatheringId(int gatheringId);
}
