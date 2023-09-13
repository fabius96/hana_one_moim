package com.hana.onemoim.community.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GalleryImageResponseDto {
    private int postId; // 게시글ID
    private int gatheringMemberId; // 모임원ID
    private String imageUrl; // 이미지URL
}
