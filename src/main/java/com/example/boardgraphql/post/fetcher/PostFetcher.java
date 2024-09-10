package com.example.boardgraphql.post.fetcher;


import com.example.boardgraphql.comment.dto.output.CommentOutput;
import com.example.boardgraphql.member.dto.output.MemberOutput;
import com.example.boardgraphql.post.dto.input.PostInput;
import com.example.boardgraphql.post.dto.output.PostOutput;
import com.example.boardgraphql.post.service.PostService;
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
public class PostFetcher {
    private final PostService postService;

    @DgsData(parentType = "Comment")
    public PostOutput post(CommentOutput commentOutput) {
        return postService.findByCommentId(commentOutput.getId());
    }

    @DgsData(parentType = "Member")
    public List<PostOutput> postList(MemberOutput memberOutput) {
        return postService.findByMemberId(memberOutput.getId());
    }


    @Secured("ROLE_NORMAL")
    @DgsMutation
    public PostOutput createPost(@InputArgument(name = "input") PostInput postInput) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        long memberId = userDetails.getId();
        return postService.createPost(memberId, postInput);
    }

    @DgsQuery
    public PostOutput findPostById(@InputArgument(name = "id") long id) {
        return postService.findPostById(id);
    }

}
