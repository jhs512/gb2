package com.gb.sapp.domain.member.member.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
    private Long id;
    private String username;
    private String password;
}
