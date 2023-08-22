package com.hana.onemoim.account.mapper;

import com.hana.onemoim.account.dto.AccountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    // 계좌개설
    void insertAccount(AccountDto accountDto);
}
