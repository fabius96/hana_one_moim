package com.hana.onemoim.community.mapper;

import com.hana.onemoim.community.dto.GalleryCommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GalleryCommentMapper {

    //댓글 작성
    void insertGalleryComment(GalleryCommentDto galleryCommentDto);

    // 갤러리 댓글 조회
    List<GalleryCommentDto> selectGalleryCommentByPostId(int postId);

}
