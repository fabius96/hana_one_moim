package com.server.openbanking.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class RequestController {

    // 오픈뱅킹 계좌조회 메서드
    @GetMapping("/openbanking/get-account-list")
    public List<Map<String, Object>> testApi(@RequestParam String personalIdNumber) {
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

}
