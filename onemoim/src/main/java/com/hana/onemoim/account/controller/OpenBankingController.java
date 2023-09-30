package com.hana.onemoim.account.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hana.onemoim.account.dto.AccountDto;
import com.hana.onemoim.member.dto.MemberDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class OpenBankingController {

    // 오픈뱅킹 페이지 조회(로그인 O)
    @GetMapping("/openbanking")
    public ModelAndView openAccount(HttpSession httpSession,
                                    HttpServletRequest httpServletRequest) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        if (memberDto == null) {
            httpSession.setAttribute("destination", httpServletRequest.getRequestURI());
            return modelAndView;
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/openbanking/get-account-list?personalIdNumber=" + memberDto.getPersonalIdNumber();
        ResponseEntity<List<AccountDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AccountDto>>() {}
        );
        List<AccountDto> accountDtoList = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        String accountDtoListAsJson = objectMapper.writeValueAsString(accountDtoList);
        modelAndView.addObject("accountDtoList", accountDtoListAsJson);
        modelAndView.setViewName("/account/openbanking");
        return modelAndView;
    }

    // 오픈뱅킹 계좌조회 메서드
    @GetMapping("/openbanking/get-account-list")
    public List<AccountDto> handleApiRequest(@RequestParam String personalIdNumber) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/openbanking/get-account-list?personalIdNumber=" + personalIdNumber;
        List<AccountDto> accountDtoList = restTemplate.getForObject(url, List.class);
        return accountDtoList;
    }
}
