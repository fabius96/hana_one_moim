package com.server.openbanking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RequestController {

    @GetMapping("/api")
    public String handleApiRequest() {
        RestTemplate restTemplate = new RestTemplate();
        // 8082 서버로 요청 전달
        String result = restTemplate.getForObject("http://localhost:8082/api", String.class);
        return result;
    }
}
