package com.gb.sapp.domain.post.post.controller;

import com.gb.sapp.domain.post.post.entity.Post;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {
    private final List<Post> posts = new ArrayList<>() {{
        add(Post.builder().id(1L).title("title 1").build());
        add(Post.builder().id(2L).title("title 2").build());
        add(Post.builder().id(3L).title("title 3").build());
    }};

    @GetMapping
    public List<Post> getItems() {
        return posts;
    }


    @Data
    public static class PostWriteReqBody {
        private String title;
    }

    @PostMapping
    public Post write(
            @RequestBody PostWriteReqBody reqBody
    ) {
        Post post = Post.builder().id((long) posts.size() + 1).title(reqBody.getTitle()).build();
        posts.add(post);
        return post;
    }
}
