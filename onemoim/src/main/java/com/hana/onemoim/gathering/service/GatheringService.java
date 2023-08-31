package com.hana.onemoim.gathering.service;

import com.hana.onemoim.gathering.dto.GatheringCreateDto;
import org.springframework.web.multipart.MultipartFile;

public interface GatheringService {

    // 모임 개설
    void createGathering(GatheringCreateDto gatheringCreateDto, MultipartFile file);
}
