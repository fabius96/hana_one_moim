package com.hana.onemoim.community.mapper;

import com.hana.onemoim.community.dto.GatheringPaymentRecordDto;
import com.hana.onemoim.community.dto.GatheringPaymentRuleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PaymentMapper {

    // 회비 확인
    int selectPaymentAmount(int gatheringId);

    // 회비규칙 확인
    GatheringPaymentRuleDto selectPaymentRule(int gatheringId);

    // 모임회비납부기록 생성
    void insertGatheringPaymentRecord(GatheringPaymentRecordDto gatheringPaymentRecordDto);

    // 모임회비납부여부 확인
    int selectPaymentRecord(Map<String, Object> params);
}
