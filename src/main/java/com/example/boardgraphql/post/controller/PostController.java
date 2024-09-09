package com.example.boardgraphql.post.controller;

import com.example.boardgraphql.comment.dto.output.CommentOutput;
import com.example.boardgraphql.member.dto.output.MemberOutput;
import com.example.boardgraphql.post.dto.output.PostOutput;
import com.example.boardgraphql.post.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PostController {
    private final PostService postService;

    @SchemaMapping(typeName = "Comment", field = "post")
    public PostOutput findByComment(CommentOutput commentOutput) {
        return postService.findByCommentId(commentOutput.getId());
    }

    @SchemaMapping(typeName = "Member", field = "postList")
    public List<PostOutput> findByMember(MemberOutput memberOutput) {
        return postService.findByMemberId(memberOutput.getId());
    }


}
