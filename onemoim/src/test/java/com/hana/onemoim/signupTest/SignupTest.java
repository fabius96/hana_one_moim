package com.hana.onemoim.signupTest;

import com.hana.onemoim.member.service.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SignupTest {

    @Autowired
    private MemberServiceImpl memberServiceImpl;

    @Test
    private void a() {
        String loginId = "11";
        Assertions.assertThat(memberServiceImpl.isLoginIdExist(loginId)).isTrue();
    }
}
