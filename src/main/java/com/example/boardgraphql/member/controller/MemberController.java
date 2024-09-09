package com.example.boardgraphql.member.controller;

import com.example.boardgraphql.comment.dto.output.CommentOutput;
import com.example.boardgraphql.member.dto.output.MemberOutput;
import com.example.boardgraphql.member.service.MemberService;
import com.example.boardgraphql.post.dto.output.PostOutput;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberController {
    private final MemberService memberService;

    @SchemaMapping(typeName = "Post", field = "member")
    public MemberOutput member(PostOutput postOutput) {
        return memberService.findById(postOutput);
    }

    @SchemaMapping(typeName = "Comment", field = "member")
    public MemberOutput memberOutput(CommentOutput commentOutput) {
        return memberService.findById(commentOutput);
    }

}
