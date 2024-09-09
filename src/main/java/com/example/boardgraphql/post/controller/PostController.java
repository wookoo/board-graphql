package com.example.boardgraphql.post.controller;

import com.example.boardgraphql.post.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PostController {
    private final PostService postService;

}
