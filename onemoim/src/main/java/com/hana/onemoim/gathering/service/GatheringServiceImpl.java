package com.hana.onemoim.gathering.service;

import com.hana.onemoim.common.dto.ImageDto;
import com.hana.onemoim.common.mapper.ImageMapper;
import com.hana.onemoim.gathering.dto.GatheringCardDto;
import com.hana.onemoim.gathering.dto.GatheringCreateDto;
import com.hana.onemoim.gathering.dto.GatheringDto;
import com.hana.onemoim.gathering.mapper.CardMapper;
import com.hana.onemoim.gathering.mapper.GatheringMapper;
import com.hana.onemoim.member.mapper.MemberMapper;
import com.hana.onemoim.util.S3uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GatheringServiceImpl implements GatheringService {

    private final GatheringMapper gatheringMapper;
    private final MemberMapper memberMapper;
    private final ImageMapper imageMapper;
    private final CardMapper cardMapper;
    private final S3uploader s3uploader;

    // 모임 개설
    @Transactional
    @Override
    public void createGathering(GatheringCreateDto gatheringCreateDto, MultipartFile file) {
        gatheringCreateDto.setGatheringId(gatheringMapper.getNextSeq() + 1); // 모임 ID 확인 및 설정
        gatheringMapper.insertGathering(gatheringCreateDto); // 모임 개설
        gatheringMapper.insertGatheringMember(gatheringCreateDto); // 모임대표(모임원 가입)

        // 모임대표이미지 삽입
        if (file != null) {
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

    // 내가 가입한 모임 조회
    @Override
    public List<GatheringDto> findAllGatheringByMemberId(int memberId) {
        List<GatheringDto> gatheringDtoList = gatheringMapper.selectGroupByMemberId(memberId);
        for (GatheringDto gatheringDto : gatheringDtoList) {
            gatheringDto.setGatheringLeaderName(memberMapper.selectNameByLeaderId(gatheringDto.getGatheringLeaderId()));
            gatheringDto.setGatheringCoverImageUrl(gatheringMapper.selectGatheringCoverImage(gatheringDto.getGatheringId()));
        }
        return gatheringDtoList;
    }

    // 모임카드 개설

    @Override
    public void createdGatheringCard(int gatheringId, String accountNumber, String gatheringName) {
        GatheringCardDto gatheringCardDto = GatheringCardDto.builder()
                .accountNumber(accountNumber)
                .cardName(gatheringName + " 모임카드")
                .cardNumber(generateCardNumber())
                .build();
        cardMapper.insertGatheringCard(gatheringCardDto);
    }

    // 모임카드번호생성
    public String generateCardNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            accountNumber.append(random.nextInt(10));
        }

        return accountNumber.toString();
    }
}
