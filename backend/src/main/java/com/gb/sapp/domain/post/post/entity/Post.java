package com.gb.sapp.domain.post.post.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private Long id;
    private String title;
}
