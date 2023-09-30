package com.bank.shinhan.controller;

import com.bank.shinhan.dto.AccountDto;
import com.bank.shinhan.service.OpenBankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResponseController {

    private final OpenBankingService openBankingService;

    @GetMapping("/openbanking/get-account-list")
    public List<AccountDto> getAccountList(@RequestParam String personalIdNumber) {
        return openBankingService.getAllAccount(personalIdNumber);
    }


    // 오픈뱅킹 연결 해제
    @PutMapping("/openbanking/disconnect-account")
    public ResponseEntity<?> updateOpenbankingRegistered(@RequestParam String accountNumber) {
        openBankingService.updateOpenbankingRegistered(accountNumber);
        return ResponseEntity.ok().body("update success");
    }
}