package com.server.openbanking.controller;

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

    // 오픈뱅킹 계좌조회 메서드
    @GetMapping("/openbanking/get-account-list")
    public List<Map<String, Object>> getAccountList(@RequestParam String personalIdNumber) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/openbanking/get-account-list?personalIdNumber=" + personalIdNumber;
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
        );
        return response.getBody();
    }

    // 오픈뱅킹 연결해제 메서드
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/openbanking/disconnect-account")
    public ResponseEntity<String> putOpenbankingRegistered(@RequestParam String accountNumber) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/openbanking/disconnect-account?accountNumber=" + accountNumber;

        HttpEntity<String> entity = new HttpEntity<>(null);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                String.class
        );

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
}
