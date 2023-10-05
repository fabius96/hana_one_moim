package com.server.openbanking.controller;

import com.server.openbanking.dto.AccountTransferDto;
import com.server.openbanking.dto.PaymentTransferWrapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class RequestController {

    // 오픈뱅킹 연결된 타행계좌조회 메서드
    @GetMapping("/openbanking/get-registered-account-list")
    public List<Map<String, Object>> getRegisteredAccountList(@RequestParam String personalIdNumber) {
        RestTemplate restTemplate = new RestTemplate();
        String targetBankUrl = "http://localhost:8082/openbanking/get-registered-account-list?personalIdNumber=" + personalIdNumber;
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                targetBankUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {
                }
        );
        return response.getBody();
    }

    // 오픈뱅킹 계좌조회 메서드
    @GetMapping("/openbanking/get-account-list")
    public List<Map<String, Object>> getAccountList(@RequestParam String personalIdNumber) {
        RestTemplate restTemplate = new RestTemplate();
        String targetBankUrl = "http://localhost:8082/openbanking/get-account-list?personalIdNumber=" + personalIdNumber;
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                targetBankUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {
                }
        );
        return response.getBody();
    }

    // 오픈뱅킹 연결해제 메서드
    @CrossOrigin(origins = {"http://localhost:8080","http://20.39.199.175"})
    @PutMapping("/openbanking/disconnect-account")
    public ResponseEntity<String> putOpenbankingRegistered(@RequestParam String accountNumber) {
        RestTemplate restTemplate = new RestTemplate();
        String targetBankUrl = "http://localhost:8082/openbanking/disconnect-account?accountNumber=" + accountNumber;

        HttpEntity<String> entity = new HttpEntity<>(null);

        ResponseEntity<String> response = restTemplate.exchange(
                targetBankUrl,
                HttpMethod.PUT,
                entity,
                String.class
        );

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    // 오픈뱅킹 연결 메서드
    @CrossOrigin(origins = {"http://localhost:8080","http://20.39.199.175"})
    @PutMapping("/openbanking/registration-account")
    public ResponseEntity<String> putOpenbankingRegisteredTrue(@RequestParam String accountNumber) {
        RestTemplate restTemplate = new RestTemplate();
        String targetBankUrl = "http://localhost:8082/openbanking/registration-account?accountNumber=" + accountNumber;

        HttpEntity<String> entity = new HttpEntity<>(null);

        ResponseEntity<String> response = restTemplate.exchange(
                targetBankUrl,
                HttpMethod.PUT,
                entity,
                String.class
        );

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    // 커뮤니티 - 오픈뱅킹 회비납부 메서드
    @PostMapping("/openbanking/payment-other")
    public ResponseEntity<Boolean> transferData(@RequestBody PaymentTransferWrapper paymentTransferWrapper) {
        RestTemplate restTemplate = new RestTemplate();
        String targetBankUrl = "http://localhost:8082/openbanking/payment-other";
        ResponseEntity<Boolean> response = restTemplate.postForEntity(targetBankUrl, paymentTransferWrapper, Boolean.class);
        boolean transferSuccess = Boolean.TRUE.equals(response.getBody());
        return ResponseEntity.ok(transferSuccess);
    }
}
