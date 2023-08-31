package com.hana.onemoim.gathering.service;

import com.hana.onemoim.common.dto.ImageDto;
import com.hana.onemoim.common.mapper.ImageMapper;
import com.hana.onemoim.gathering.dto.GatheringCreateDto;
import com.hana.onemoim.gathering.mapper.GatheringMapper;
import com.hana.onemoim.util.S3uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GatheringServiceImpl implements GatheringService {

    private final GatheringMapper gatheringMapper;
    private final ImageMapper imageMapper;
    private final S3uploader s3uploader;

    // 모임 개설
    @Override
    public void createGathering(GatheringCreateDto gatheringCreateDto, MultipartFile file) {
        gatheringCreateDto.setGatheringId(gatheringMapper.getNextSeq()+1); // 모임 ID 확인 및 설정
        gatheringMapper.insertGathering(gatheringCreateDto); // 모임 개설

        if(file != null){
            try {
                String url = s3uploader.Uploader(file);
                ImageDto imageDto = ImageDto.builder()
                        .targetId(gatheringCreateDto.getGatheringId())
                        .imageUrl(url)
                        .build();
                imageMapper.insertImage(imageDto);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("이미지 업로드 중 오류가 발생했습니다.");
            }

        }

        gatheringMapper.insertGatheringPaymentRule(gatheringCreateDto); // 모임회비규칙 생성
    }
}
