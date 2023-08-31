package com.hana.onemoim.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ImageDto {

    private int targetId; // 대상ID(모임ID, 게시글ID)
    private String imageUrl; // URL

}
