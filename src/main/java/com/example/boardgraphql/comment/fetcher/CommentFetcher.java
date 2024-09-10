package com.example.boardgraphql.comment.fetcher;


import com.example.boardgraphql.comment.dto.input.CommentInput;
import com.example.boardgraphql.comment.dto.output.CommentOutput;
import com.example.boardgraphql.comment.service.CommentService;
import com.example.boardgraphql.member.dto.output.MemberOutput;
import com.example.boardgraphql.post.dto.output.PostOutput;
import com.example.boardgraphql.security.CustomUserDetail;
import com.netflix.graphql.dgs.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@DgsComponent
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentFetcher {
    private final CommentService commentService;


    @DgsData(parentType = "Member", field = "commentList")
    public List<CommentOutput> commentListByMember(DgsDataFetchingEnvironment dgs) {
        MemberOutput memberOutput = dgs.getSource();
        return commentService.findByMemberId(memberOutput.getId());
    }

    @DgsData(parentType = "Post")
    public List<CommentOutput> commentList(DgsDataFetchingEnvironment dgs) {
        PostOutput postOutput = dgs.getSource();
        return commentService.findByPostId(postOutput.getId());
    }


    @DgsMutation
    public CommentOutput createCommentByPostId(@InputArgument(name = "input") CommentInput commentInput) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        long memberId = userDetails.getId();
        return commentService.createComment(memberId, commentInput);
    }

    @Secured("ROLE_NORMAL")
    @DgsMutation
    public boolean deleteCommentById(@InputArgument(name = "id") long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        long memberId = userDetails.getId();
        return commentService.deleteCommentByMemberIdAndId(memberId, id);
    }

}
