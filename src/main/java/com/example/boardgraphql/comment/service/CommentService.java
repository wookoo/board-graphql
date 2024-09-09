package com.example.boardgraphql.comment.service;

import com.example.boardgraphql.comment.dto.input.CommentInput;
import com.example.boardgraphql.comment.dto.output.CommentOutput;
import com.example.boardgraphql.comment.entity.Comment;
import com.example.boardgraphql.member.entity.Member;
import com.example.boardgraphql.member.service.MemberService;
import com.example.boardgraphql.post.entity.Post;
import com.example.boardgraphql.post.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentService {

    private final MemberService memberService;

    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentOutput createComment(long memberId, CommentInput commentInput) {
        Member member = memberService.findById(memberId);
        Post post = postService.findById(commentInput.getPostId());
        Comment comment = commentInput.toComment(post, member);
        commentRepository.save(comment);
        return CommentOutput.from(comment);
    }
}
