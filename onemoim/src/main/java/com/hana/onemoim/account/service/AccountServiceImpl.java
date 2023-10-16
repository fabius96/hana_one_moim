package com.hana.onemoim.account.service;

import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.account.dto.AccountTransferDto;
import com.hana.onemoim.account.dto.GatheringAccountDto;
import com.hana.onemoim.account.mapper.AccountMapper;
import com.hana.onemoim.account.dto.MemberTransactionDto;
import com.hana.onemoim.account.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final int BANK_CODE_HANA = 20; // 은행코드 - 하나은행
    private static final int ACCOUNT_STATUS_CODE_ACTIVE = 30; // 계좌상태코드 - 활동
    private static final int ACCOUNT_TYPE_CODE_INOUT = 42; // 계좌종류코드 - 입출금
    private static final int TRANSACTION_TYPE_CODE_DEPOSIT = 50;   // 거래유형코드 - 입금
    private static final int TRANSACTION_TYPE_CODE_WITHDRAW = 51;  // 거래유형코드 - 출금

    private final AccountMapper accountMapper;
    private final TransactionMapper transactionMapper;

    // 계좌개설
    @Override
    public void openAccount(int memberId, String simplePassword, String accountNickname) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(generateAccountNumber()); // 계좌번호 생성
        accountDto.setMemberId(memberId); // 회원ID
        accountDto.setBankCode(BANK_CODE_HANA); // 하나은행
        accountDto.setAccountStatusCode(ACCOUNT_STATUS_CODE_ACTIVE);  // 활동
        accountDto.setAccountTypeCode(ACCOUNT_TYPE_CODE_INOUT); // 입출금
        accountDto.setAccountPassword(simplePassword); // 비밀번호
        accountDto.setAccountNickname(accountNickname); // 계좌별칭
        accountMapper.insertAccount(accountDto);
    }

    // 모임통장 개설
    @Override
    public void openGatheringAccount(String simplePassword, String accountNickname, int gatheringId) {
        GatheringAccountDto gatheringAccountDto = GatheringAccountDto.builder()
                .gatheringId(gatheringId) // 모임ID
                .accountNickname(accountNickname) // 계좌별칭
                .accountPassword(simplePassword) // 비밀번호
                .accountNumber(generateAccountNumber()) // 계좌번호 생성
                .build();
        accountMapper.insertGatheringAccount(gatheringAccountDto);
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

    // 계좌이체
    @Transactional
    @Override
    public void accountTransfer(AccountTransferDto accountTransferDto) {
        processWithdrawal(accountTransferDto);  // 출금 처리
        processDeposit(accountTransferDto);     // 입금 처리
    }

    // 출금 처리
    private void processWithdrawal(AccountTransferDto accountTransferDto) {
        accountMapper.updateAccountBalance(accountTransferDto); // 출금을 위한 잔액 업데이트
        // 거래 후 잔액
        int balanceAfterTransaction = accountMapper.selectBalance(accountTransferDto);
        createTransaction(accountTransferDto, TRANSACTION_TYPE_CODE_WITHDRAW, balanceAfterTransaction);  // 출금 거래 기록 생성
    }

    // 입금 처리
    private void processDeposit(AccountTransferDto accountTransferDto) {
        accountMapper.updateAccountBalanceDeposit(accountTransferDto); // 입금을 위한 잔액 업데이트
        AccountTransferDto depositDto = new AccountTransferDto();
        depositDto.setAccountNumber(accountTransferDto.getOtherAccountNumber());
        // 거래 후 잔액
        int balanceAfterTransaction = accountMapper.selectBalance(depositDto);
        createTransaction(accountTransferDto, TRANSACTION_TYPE_CODE_DEPOSIT, balanceAfterTransaction); // 입금 거래 기록 생성
    }

    // 거래 내역 생성
    private void createTransaction(AccountTransferDto accountTransferDto, int transactionType, int balanceAfterTransaction) {
        MemberTransactionDto transactionDto = new MemberTransactionDto();
        if (transactionType == TRANSACTION_TYPE_CODE_DEPOSIT) { // 입금
            transactionDto.setAccountNumber(accountTransferDto.getOtherAccountNumber());
            transactionDto.setOtherAccountNumber(accountTransferDto.getAccountNumber());
        } else { // 출금
            transactionDto.setAccountNumber(accountTransferDto.getAccountNumber());
            transactionDto.setOtherAccountNumber(accountTransferDto.getOtherAccountNumber());
        }
        transactionDto.setTransactionTypeCode(transactionType);  // 거래 유형
        transactionDto.setTransactionAmount(accountTransferDto.getAmount());  // 거래 금액
        transactionDto.setBalanceAfterTransaction(balanceAfterTransaction);  // 거래 후 잔액
        transactionDto.setMemo(accountTransferDto.getMemo()); // 거래 메모
        transactionMapper.insertTransaction(transactionDto);
    }

    // 거래내역조회
    @Override
    public List<MemberTransactionDto> findTransactionByAccountNumber(String accountNumber, int month) {
        return transactionMapper.selectTransactionByAccountNumber(accountNumber, month);
    }

    // 모임계좌번호조회
    @Override
    public String findAccountNumberByGatheringId(int gatheringId) {
        return accountMapper.selectAccountNumberByGatheringId(gatheringId);
    }
}
