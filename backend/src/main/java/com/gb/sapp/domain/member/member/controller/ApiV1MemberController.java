package com.gb.sapp.domain.member.member.controller;

import com.gb.sapp.domain.member.member.entity.Member;
import com.gb.sapp.global.rq.Rq;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MemberController {
    private final Rq rq;

    private final List<Member> members = new ArrayList<>() {{
        add(Member.builder().id(1L).username("system").password("1234").build());
        add(Member.builder().id(2L).username("admin").password("1234").build());
        add(Member.builder().id(3L).username("user1").password("1234").build());
        add(Member.builder().id(4L).username("user2").password("1234").build());
        add(Member.builder().id(5L).username("user3").password("1234").build());
    }};

    @Data
    public static class MemberLoginReqBody {
        private String username;
        private String password;
    }

    @PostMapping("/login")
    public Member login(
            @RequestBody MemberLoginReqBody reqBody
    ) {
        Member member = members.stream()
                .filter(_member -> _member.getUsername().equals(reqBody.getUsername()) && _member.getPassword().equals(reqBody.getPassword()))
                .findFirst()
                .orElse(null);

        if (member == null) {
            throw new RuntimeException("Invalid username or password");
        }

        rq.setCrossDomainCookie("memberId", member.getId().toString());

        return member;
    }
}
