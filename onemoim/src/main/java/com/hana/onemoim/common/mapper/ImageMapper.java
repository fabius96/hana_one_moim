package com.hana.onemoim.common.mapper;

import com.hana.onemoim.common.dto.ImageDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {

    // 이미지 저장
    public void insertImage(ImageDto imageDto);
}