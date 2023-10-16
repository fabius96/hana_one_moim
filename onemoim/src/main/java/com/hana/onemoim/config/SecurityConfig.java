package com.hana.onemoim.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // CSRF 공격 방어 기능을 비활성화
                .authorizeRequests()  // HttpServletRequest에 따라 접근을 제한
                .antMatchers("/**").permitAll()  // 모든 요청에 대해 접근을 허용
                .anyRequest().authenticated();  // 그 외의 요청은 인증이 필요함
    }

}
