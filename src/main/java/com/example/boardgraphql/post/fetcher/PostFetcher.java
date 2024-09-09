package com.example.boardgraphql.post.fetcher;


import com.example.boardgraphql.post.service.PostService;
import com.netflix.graphql.dgs.DgsComponent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PostFetcher {
    private final PostService postService;
}
