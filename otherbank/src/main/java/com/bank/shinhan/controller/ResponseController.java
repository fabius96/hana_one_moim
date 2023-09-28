package com.bank.shinhan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    @GetMapping("/api")
    public String handleApiRequest() {
        return "Hello from 8082 server!";
    }
}