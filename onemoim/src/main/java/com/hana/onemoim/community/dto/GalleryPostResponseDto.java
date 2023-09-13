package com.hana.onemoim.community.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GalleryPostResponseDto {
    private int postId; // 게시글ID
    private int gatheringId; // 모임ID
    private int gatheringMemberId; // 모임원ID
    private String gatheringMemberName; // 모임원 이름
    private String title; // 제목
    private String content; // 내용
    private int viewCnt; // 조회수
    private int commentCnt; // 댓글수
    private List<GalleryCommentDto> galleryCommentDtoList; // 댓글목록
    private String createdAt; // createdAt
    private String modifiedAt; // modifiedAt
    private List<String> imageUrlList; // 이미지URL
}
