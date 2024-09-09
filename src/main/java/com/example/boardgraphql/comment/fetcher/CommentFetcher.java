package com.example.boardgraphql.comment.fetcher;


import com.example.boardgraphql.comment.service.CommentService;
import com.netflix.graphql.dgs.DgsComponent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentFetcher {
    private final CommentService commentService;
}
