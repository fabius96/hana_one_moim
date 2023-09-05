package com.hana.onemoim.gathering.service;

import com.hana.onemoim.gathering.dto.CardBenefitWrapper;
import com.hana.onemoim.gathering.dto.GatheringCreateDto;
import com.hana.onemoim.gathering.dto.GatheringDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GatheringService {

    // 모임 개설
    void createGathering(GatheringCreateDto gatheringCreateDto, MultipartFile file);

    // 모임 조회
    List<GatheringDto> findAllGatheringByMemberId(int memberId);

    // 모임카드 개설
    int createdGatheringCard(int gatheringId, String accountNumber, String gatheringName);

    // 카드 혜택 설정
    void settingCardBenefit(CardBenefitWrapper cardBenefitWrapper, int gatheringCardId);

    // 모임 검색
    List<GatheringDto> findAllGatheringByKeyword(String keyword);

    // 모임 검색 결과 수
    int countGatheringByKeyword(String keyword);

    // 모임 관심사 설정
    void registerGatheringInterest(int gatheringId, List<String> interestNames);
}
