package com.bank.shinhan.mapper;

import com.bank.shinhan.dto.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {


    // 전체계좌조회
    List<AccountDto> selectAccountByPersonalIdNumber(String personalIdNumber);

}
