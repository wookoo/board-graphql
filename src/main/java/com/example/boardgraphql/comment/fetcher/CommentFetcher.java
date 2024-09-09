package com.example.boardgraphql.comment.fetcher;


import com.example.boardgraphql.comment.dto.input.CommentInput;
import com.example.boardgraphql.comment.dto.output.CommentOutput;
import com.example.boardgraphql.comment.service.CommentService;
import com.example.boardgraphql.security.CustomUserDetail;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@DgsComponent
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentFetcher {
    private final CommentService commentService;


    @DgsMutation
    public CommentOutput createCommentByPostId(@InputArgument(name = "input") CommentInput commentInput) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        long memberId = userDetails.getId();
        return commentService.createComment(memberId, commentInput);
    }
}
