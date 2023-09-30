package com.bank.shinhan.controller;

import com.bank.shinhan.dto.AccountDto;
import com.bank.shinhan.service.OpenBankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResponseController {

    private final OpenBankingService openBankingService;

    @GetMapping("/openbanking/get-account-list")
    public List<AccountDto> handleApiRequest(String personalIdNumber) {
        return openBankingService.getAllAccount(personalIdNumber);
    }
}