package com.hana.onemoim.community.mapper;

import com.hana.onemoim.community.dto.GalleryPostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GalleryPostMapper {

    // post_seq.NEXTVAL() 가져오기
    int getNextPostSeq();

    // 갤러리 게시글 작성 삽입
    void insertGalleryPost(GalleryPostDto galleryPostDto);

    // post_id 가져오기
    List<Integer> selectPostId(int gatheringId);

    // 게시글 작성자 조회
    int selectWriter(int postId);

    // 게시글 상세 조회
    GalleryPostDto selectGalleryPost(int postId);

    // 게시글 조회수 증가
    void updateViewCnt(int postId);
}
