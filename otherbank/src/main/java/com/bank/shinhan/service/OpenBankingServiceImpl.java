package com.bank.shinhan.service;

import com.bank.shinhan.dto.AccountDto;
import com.bank.shinhan.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenBankingServiceImpl implements OpenBankingService{
    private final AccountMapper accountMapper;

    // 오픈뱅킹 계좌조회 메서드
    @Override
    public List<AccountDto> getAllAccount(String personalIdNumber) {
        return accountMapper.selectAccountByPersonalIdNumber(personalIdNumber);
    }
}
