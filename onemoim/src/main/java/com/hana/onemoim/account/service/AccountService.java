package com.hana.onemoim.account.service;

import com.hana.onemoim.account.dto.AccountDto;

import java.util.List;

public interface AccountService {

    // 계좌 개설
    void openAccount(int memberId, String simplePassword, String accountNickname);

    // 전체 계좌 조회
    List<AccountDto> findAllAccount(String personalIdNumber);
}
