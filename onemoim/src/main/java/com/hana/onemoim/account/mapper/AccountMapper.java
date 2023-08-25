package com.hana.onemoim.account.mapper;

import com.hana.onemoim.account.dto.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    // 계좌개설
    void insertAccount(AccountDto accountDto);

    // 전체계좌조회
    List<AccountDto> selectAccountByPersonalIdNumber(String personalIdNumber);
}
