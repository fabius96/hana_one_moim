package com.hana.onemoim.community.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GalleryCommentDto {
    private int commentId; // 댓글ID
    private int postId; // 게시글ID
    private int gatheringId; // 모임ID
    private int gatheringMemberId; // 모임원ID
    private String memberName; // 모임원 이름
    private String content; // 내용
    private String createdAt; // createdAt
    private String modifiedAt; // modifiedAt

    @Builder
    public GalleryCommentDto(int postId, int gatheringId, int gatheringMemberId, String content) {
        this.postId = postId;
        this.gatheringId = gatheringId;
        this.gatheringMemberId = gatheringMemberId;
        this.content = content;
    }
}
