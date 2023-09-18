package com.hana.onemoim.community.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GalleryPostDto {
    private int postId; // 게시글ID
    private int gatheringId; // 모임ID
    private int gatheringMemberId; // 모임원ID
    private String title; // 제목
    private String content; // 내용
    private int viewCnt; // 조회수
    private String isNotice; // 공지사항여부
    private String createdAt; // createdAt
    private String modifiedAt; // modifiedAt
}
