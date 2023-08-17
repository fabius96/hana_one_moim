package com.hana.onemoim.signupTest;

import com.hana.onemoim.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SignupTest {

    @Autowired
    private MemberService memberService;

    @Test
    private void a() {
        String loginId = "11";
        Assertions.assertThat(memberService.isLoginIdExist(loginId)).isTrue();
    }
}
