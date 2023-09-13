package com.hana.onemoim.common.mapper;

import com.hana.onemoim.common.dto.ImageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {

    // 이미지 저장
    void insertImage(ImageDto imageDto);
    void insertGalleryImage(ImageDto imageDto);

    // 이미지 URL 조회
    String selectImgUrlByPostId(int postId);

    // 상세 페이지용 이미지 URL 조회
    List<String> selectImgUrlForDetail(int postId);
}
