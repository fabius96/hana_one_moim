package com.hana.onemoim.gathering.service;

import com.hana.onemoim.common.dto.ImageDto;
import com.hana.onemoim.common.dto.InterestDto;
import com.hana.onemoim.common.mapper.ImageMapper;
import com.hana.onemoim.common.mapper.InterestMapper;
import com.hana.onemoim.community.dto.GatheringMemberDto;
import com.hana.onemoim.gathering.dto.*;
import com.hana.onemoim.gathering.mapper.CardMapper;
import com.hana.onemoim.gathering.mapper.GatheringMapper;
import com.hana.onemoim.member.dto.MemberDto;
import com.hana.onemoim.member.mapper.MemberMapper;
import com.hana.onemoim.util.S3uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GatheringServiceImpl implements GatheringService {

    private static final int MEMBER_STATUS_ACTIVE = 70;  // 활동
    private static final int MEMBER_STATUS_PAUSE = 71;   // 정지
    private static final int MEMBER_STATUS_STANDBY = 72;   // 정지

    private final GatheringMapper gatheringMapper;
    private final MemberMapper memberMapper;
    private final ImageMapper imageMapper;
    private final CardMapper cardMapper;
    private final InterestMapper interestMapper;
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
        return makeGatheringDtos(gatheringDtoList);
    }

    // 모임카드 개설

    @Override
    public int createdGatheringCard(int gatheringId, String accountNumber, String gatheringName) {
        int gatheringCardId = cardMapper.getNextCardSeq() + 1;
        GatheringCardDto gatheringCardDto = GatheringCardDto.builder()
                .cardId(gatheringCardId)
                .accountNumber(accountNumber)
                .cardName(gatheringName + " 모임카드")
                .cardNumber(generateCardNumber())
                .build();
        cardMapper.insertGatheringCard(gatheringCardDto);
        return gatheringCardId;
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

    // 카드혜택설정

    @Override
    public void settingCardBenefit(CardBenefitWrapper cardBenefitWrapper, int gatheringCardId) {
        List<CardBenefitDto> cardBenefitDtoList = cardBenefitWrapper.getCardBenefitDtoList();
        for (CardBenefitDto cardBenefitDto : cardBenefitDtoList) {
            cardBenefitDto.setCardId(gatheringCardId);
            cardMapper.insertCardBenefit(cardBenefitDto);
        }
    }

    // 모임 검색
    @Override
    public List<GatheringDto> findAllGatheringByKeyword(String keyword, int memberId) {
        List<GatheringDto> gatheringDtoList = gatheringMapper.selectGatheringByKeyword(keyword);
        for (GatheringDto gatheringDto : gatheringDtoList) {
            int gatheringId = gatheringDto.getGatheringId();
            boolean isJoined = gatheringMapper.isMemberJoined(gatheringId, memberId);
            gatheringDto.setJoined(isJoined);
        }
        return makeGatheringDtos(gatheringDtoList);
    }

    // 모임 검색 결과 세기
    @Override
    public int countGatheringByKeyword(String keyword) {
        return gatheringMapper.countGatheringByKeyword(keyword);
    }

    // 모임 관심사 등록
    @Override
    public void registerGatheringInterest(int gatheringId, List<String> interestNames) {
        for (String interestName : interestNames) {
            InterestDto interestDto = new InterestDto();
            interestDto.setGatheringId(gatheringId);
            interestDto.setInterestName(interestName);
            interestMapper.insertInterest(interestDto);
        }
    }

    // 모임 분류 조회
    @Override
    public List<GatheringDto> findGatheringByInterest(String interest, int memberId) {
        List<GatheringDto> gatheringDtoList = new ArrayList<>();
        List<Integer> list = interestMapper.selectGatheringIdFromInterest(interest);
        for (int gatheringId : list) {
            GatheringDto gatheringDto = findGatheringByGatheringId(true, gatheringId);
            gatheringDto.setGatheringLeaderName(memberMapper.selectNameByLeaderId(gatheringDto.getGatheringLeaderId()));

            boolean isJoined = gatheringMapper.isMemberJoined(gatheringId, memberId);
            gatheringDto.setJoined(isJoined);

            gatheringDtoList.add(gatheringDto);
        }
        makeGatheringDtos(gatheringDtoList);
        return gatheringDtoList;
    }

    // 모임 추천
    @Override
    public List<GatheringDto> findGatheringByMemberInterest(int memberId, int num) {
        List<GatheringDto> gatheringDtoList = new ArrayList<>();
        List<Integer> list = interestMapper.selectGatheringIdByMemberInterest(memberId);
        list.removeIf(Objects::isNull);
        for (int gatheringId : list) {
            GatheringDto gatheringDto = findGatheringByGatheringId(true, gatheringId);
            if (gatheringDto == null) {
                continue;
            }
            gatheringDto.setGatheringCoverImageUrl(gatheringMapper.selectGatheringCoverImage(gatheringDto.getGatheringId()));
            gatheringDtoList.add(gatheringDto);
            if (num == 1) {
                if (gatheringDtoList.size() == 4) {
                    break;
                }
            }
        }
        return gatheringDtoList;
    }

    // 모임 설명 개행, 모임장명, 모임커버이미지URL 설정 공통 모듈화
    private List<GatheringDto> makeGatheringDtos(List<GatheringDto> gatheringDtoList) {
        for (GatheringDto gatheringDto : gatheringDtoList) {
//            String gatheringDescription = gatheringDto.getGatheringDescription().replace("\n", "<br/>");
//            gatheringDto.setGatheringDescription(gatheringDescription);
            gatheringDto.setGatheringLeaderName(memberMapper.selectNameByLeaderId(gatheringDto.getGatheringLeaderId()));
            gatheringDto.setGatheringCoverImageUrl(gatheringMapper.selectGatheringCoverImage(gatheringDto.getGatheringId()));
        }
        return gatheringDtoList;
    }

    // 모임 ID로 모임 조회
    @Override
    public GatheringDto findGatheringByGatheringId(boolean onlyPublic, int gatheringId) {
        GatheringDto gatheringDto = gatheringMapper.selectGatheringByGatheringId(onlyPublic, gatheringId);
        return gatheringDto;
    }

    // 모임 ID로 모임 조회(+모달용)
    @Override
    public GatheringDto getGatheringInfoForModal(boolean onlyPublic, int gatheringId, int memberId) {
        GatheringDto gatheringDto = findGatheringByGatheringId(onlyPublic, gatheringId);
        gatheringDto.setInterestList(interestMapper.selectInterestNameByGatheringId(gatheringId));
        gatheringDto.setGatheringCoverImageUrl(gatheringMapper.selectGatheringCoverImage(gatheringDto.getGatheringId()));
        gatheringDto.setGatheringLeaderName(memberMapper.selectNameByLeaderId(gatheringDto.getGatheringLeaderId()));
        boolean isJoined = gatheringMapper.isMemberJoined(gatheringId, memberId);
        gatheringDto.setJoined(isJoined);

        return gatheringDto;
    }

    // 모임장 ID 확인
    @Override
    public int getGatheringLeaderId(int gatheringId) {
        return gatheringMapper.selectGatheringLeaderId(gatheringId);
    }

    // 모임 가입
    @Override
    public void joinGathering(int gatheringId, MemberDto memberDto) {
        GatheringMemberDto gatheringMemberDto = GatheringMemberDto.builder()
                .gatheringId(gatheringId)
                .memberId(memberDto.getMemberId())
                .memberStatusCode(MEMBER_STATUS_STANDBY)
                .memberName(memberDto.getName())
                .build();
        gatheringMapper.insertGatheringMemberByApplication(gatheringMemberDto);
    }
}
