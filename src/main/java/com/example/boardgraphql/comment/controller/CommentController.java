package com.example.boardgraphql.comment.controller;

import com.example.boardgraphql.comment.dto.output.CommentOutput;
import com.example.boardgraphql.comment.service.CommentService;
import com.example.boardgraphql.member.dto.output.MemberOutput;
import com.example.boardgraphql.post.dto.output.PostOutput;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentController {

    private final CommentService commentService;

    @SchemaMapping(typeName = "Member", field = "commentList")
    public List<CommentOutput> commentList(MemberOutput memberOutput) {

        return commentService.findByMemberId(memberOutput.getId());
    }

    @SchemaMapping(typeName = "Post", field = "commentList")
    public List<CommentOutput> commentOutputList(PostOutput postOutput) {
        return commentService.findByPostId(postOutput.getId());
    }
}
