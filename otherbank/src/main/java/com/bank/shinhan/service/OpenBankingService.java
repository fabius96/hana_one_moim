package com.bank.shinhan.service;

import com.bank.shinhan.dto.AccountDto;

import java.util.List;

public interface OpenBankingService {

    // 오픈뱅킹 연견된  전체계좌조회 메서드
    List<AccountDto> getAllRegisteredAccount(String personalIdNumber);

    // 전체계좌조회 메서드
    List<AccountDto> getAllAccount(String personalIdNumber);

    // 오픈뱅킹 연결해제 메서드
    void updateOpenbankingRegistered(String accountNumber);

    // 오픈뱅킹 연결 메서드
    void updateOpenbankingRegisteredTrue(String accountNumber);
}
