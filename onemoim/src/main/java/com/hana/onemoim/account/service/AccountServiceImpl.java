package com.hana.onemoim.account.service;

import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.account.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountMapper accountMapper;

    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    // 계좌개설

    @Override
    public void openAccount(int memberId, String simplePassword, String accountNickname) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(generateAccountNumber());
        accountDto.setMemberId(memberId);
        accountDto.setBankCode(20); // 하나은행
        accountDto.setAccountStatusCode(30);  // 활동
        accountDto.setAccountTypeCode(42); // 입출금
        accountDto.setAccountPassword(simplePassword);
        accountDto.setAccountNickname(accountNickname);
        accountMapper.insertAccount(accountDto);
    }

    // 계좌번호 생성
    public String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();

        for (int i = 0; i < 14; i++) {
            accountNumber.append(random.nextInt(10));
        }

        return accountNumber.toString();
    }

    // 전체계좌조회

    @Override
    public List<AccountDto> findAllAccount(String personalIdNumber) {
        return accountMapper.selectAccountByPersonalIdNumber(personalIdNumber);
    }
}
