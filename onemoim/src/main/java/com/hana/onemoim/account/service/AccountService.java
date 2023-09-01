package com.hana.onemoim.account.service;

import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.account.dto.AccountTransferDto;
import com.hana.onemoim.account.dto.MemberTransactionDto;

import java.util.List;

public interface AccountService {

    // 계좌 개설
    void openAccount(int memberId, String simplePassword, String accountNickname);

    // 모임 계좌 개설
    void openGatheringAccount(String simplePassword, String accountNickname, int gatheringId);

    // 전체 계좌 조회
    List<AccountDto> findAllAccount(String personalIdNumber);

    // 계좌이체
    void accountTransfer(AccountTransferDto accountTransferDto);

    // 거래내역 조회
    List<MemberTransactionDto> findTransactionByAccountNumber(AccountDto accountDto);

    // 모임계좌번호조회
    String findAccountNumberByGatheringId(int gatheringId);
}
