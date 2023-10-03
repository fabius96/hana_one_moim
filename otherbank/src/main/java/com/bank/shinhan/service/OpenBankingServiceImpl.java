package com.bank.shinhan.service;

import com.bank.shinhan.dto.AccountDto;
import com.bank.shinhan.dto.AccountTransferDto;
import com.bank.shinhan.dto.MemberTransactionDto;
import com.bank.shinhan.mapper.AccountMapper;
import com.bank.shinhan.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenBankingServiceImpl implements OpenBankingService {

    private static final int TRANSACTION_TYPE_WITHDRAW = 51;  // 출금 거래 코드
    private static final int TRANSACTION_TYPE_DEPOSIT = 50;   // 입금 거래 코드
    private final AccountMapper accountMapper;
    private final TransactionMapper transactionMapper;

    // 오픈뱅킹 계좌조회 메서드
    @Override
    public List<AccountDto> getAllRegisteredAccount(String personalIdNumber) {
        return accountMapper.selectRegisteredAccountByPersonalIdNumber(personalIdNumber);
    }

    // 전체계좌조회 메서드
    @Override
    public List<AccountDto> getAllAccount(String personalIdNumber) {
        return accountMapper.selectAccountByPersonalIdNumber(personalIdNumber);
    }

    // 오픈뱅킹 연결해제 메서드
    @Override
    public void updateOpenbankingRegistered(String accountNumber) {
        accountMapper.updateOpenbankingRegistered(accountNumber);
    }

    // 오픈뱅킹 연결 메서드
    @Override
    public void updateOpenbankingRegisteredTrue(String accountNumber) {
        accountMapper.updateOpenbankingRegisteredTrue(accountNumber);
    }

    // 커뮤니티 - 오픈뱅킹 화비납입
    @Override
    public boolean paymentTransfer(AccountTransferDto accountTransferDto, int gatheringId, int gatheringMemberId) {
        try {
            processWithdrawal(accountTransferDto, gatheringId, gatheringMemberId);  // 출금 처리
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 출금 프로세스
    private void processWithdrawal(AccountTransferDto accountTransferDto, int gatheringId, int gatheringMemberId) {
        accountMapper.updateAccountBalance(accountTransferDto); // 출금을 위한 잔액 업데이트
        int balanceAfterTransaction = accountMapper.selectBalance(accountTransferDto);
        createTransaction(accountTransferDto, balanceAfterTransaction, gatheringId, gatheringMemberId);  // 출금 거래 기록 생성
    }

    // 거래내역 생성 및 모임회비 납부기록 생성
    private void createTransaction(AccountTransferDto accountTransferDto, int balanceAfterTransaction
            , int gatheringId, int gatheringMemberId) {

        transactionMapper.insertTransaction( // 출금계좌(회원계좌) 기준
                MemberTransactionDto.builder()
                        .accountNumber(accountTransferDto.getAccountNumber())
                        .otherAccountNumber(accountTransferDto.getOtherAccountNumber())
                        .transactionTypeCode(TRANSACTION_TYPE_WITHDRAW)
                        .transactionAmount(accountTransferDto.getAmount())
                        .balanceAfterTransaction(balanceAfterTransaction)
                        .memo(accountTransferDto.getMemo())
                        .build());
    }
}

