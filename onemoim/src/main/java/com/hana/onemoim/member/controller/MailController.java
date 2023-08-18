package com.hana.onemoim.member.controller;

import com.hana.onemoim.member.service.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/mail")
public class MailController {

    @Autowired
    MailServiceImpl mailServiceImpl;

    //127.0.0.1:8080/ROOT/api/mail/confirm.json?email
    @PostMapping(value = "/confirm")
    public String mailConfirm(@RequestParam(name = "email") String email) throws Exception{
        String code = mailServiceImpl.sendSimpleMessage(email);
        System.out.println("사용자에게 발송한 인증코드 ==> " + code);

        return code;
    }
}
