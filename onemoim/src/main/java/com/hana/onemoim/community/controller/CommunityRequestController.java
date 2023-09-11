package com.hana.onemoim.community.controller;

import com.hana.onemoim.community.service.CommunityService;
import com.hana.onemoim.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommunityRequestController {

    private final CommunityService communityService;

    // 모임원 상태 코드 변경
    @PatchMapping("/community/update-status")
    public ResponseEntity<?> updateGatheringMemberStatusCode(HttpSession httpSession,
                                                             @RequestParam("memberStatusCode") int memberStatusCode,
                                                             @RequestParam("memberId") int memberId,
                                                             @RequestParam("gatheringId") int gatheringId) {
        MemberDto memberDto = (MemberDto) httpSession.getAttribute("loggedInMember");

        communityService.updateMemberStatusCode(memberStatusCode, memberId, gatheringId);

        return ResponseEntity.ok().body(Map.of("message", "모임원 상태가 정상적으로 변경되었습니다."));
    }
}
