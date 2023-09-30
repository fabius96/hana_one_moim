package com.bank.shinhan.service;

import com.bank.shinhan.dto.AccountDto;

import java.util.List;

public interface OpenBankingService {

    // 오픈뱅킹 계좌조회 메서드
    List<AccountDto> getAllAccount(String personalIdNumber);

    // 오픈뱅킹 연결해제 메서드
    void updateOpenbankingRegistered(String accountNumber);
}
