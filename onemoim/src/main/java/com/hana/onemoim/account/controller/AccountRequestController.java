package com.hana.onemoim.account.controller;

import com.hana.onemoim.account.dto.AccountTransferDto;
import com.hana.onemoim.account.service.AccountService;
import com.hana.onemoim.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class AccountRequestController {
    private final AccountService accountService;

    // 금융상품 개설(모임 개설 Process 03)
    @PostMapping("/account/account-opening")
    public ModelAndView makeAccount(@RequestParam int memberId,
                                    @RequestParam String simplePassword,
                                    @RequestParam String accountNickname,
                                    @RequestParam(required = false) int gatheringId,
                                    @RequestParam(required = false) String gatheringName) {
        ModelAndView modelAndView = new ModelAndView("/account/account-opening-ok");
        // 모임통장 개설일 경우
        if (gatheringId != 0) {
            accountNickname = gatheringName + " 모임통장";
            accountService.openGatheringAccount(simplePassword, accountNickname, gatheringId);
            modelAndView.addObject("gatheringName", gatheringName);
            modelAndView.addObject("gatheringId", gatheringId);
            modelAndView.addObject("accountNumber",
                    accountService.findAccountNumberByGatheringId(gatheringId));
            modelAndView.setViewName("/gathering/card-opening");
        } else { // 개인계좌 개설일 경우
            accountService.openAccount(memberId, simplePassword, accountNickname);
        }
        return modelAndView;
    }

    // 계좌이체
    @PostMapping("/account/account-transfer-hana")
    public ModelAndView accountTransfer(HttpSession httpSession,
                                        @ModelAttribute AccountTransferDto accountTransferDto) {
        ModelAndView modelAndView = new ModelAndView("/signin");
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");
        if (memberDto == null) {
            return modelAndView;
        }
        modelAndView.setViewName("/account/account-transfer-ok");
        accountService.accountTransfer(accountTransferDto);
        return modelAndView;
    }
}
