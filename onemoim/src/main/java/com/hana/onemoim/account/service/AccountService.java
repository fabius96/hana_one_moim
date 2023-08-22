package com.hana.onemoim.account.service;

public interface AccountService {

    // 계좌 개설
    public void openAccount(int memberId, String simplePassword, String accountNickname);
}
