package com.hana.onemoim.gathering.service;

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
    void createdGatheringCard(int gatheringId, String accountNumber, String gatheringName);
}
