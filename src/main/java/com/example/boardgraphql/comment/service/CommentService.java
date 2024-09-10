package com.example.boardgraphql.comment.service;

import com.example.boardgraphql.comment.dto.input.CommentInput;
import com.example.boardgraphql.comment.dto.output.CommentOutput;
import com.example.boardgraphql.comment.entity.Comment;
import com.example.boardgraphql.member.entity.Member;
import com.example.boardgraphql.member.service.MemberRepository;
import com.example.boardgraphql.post.entity.Post;
import com.example.boardgraphql.post.service.PostRepository;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentOutput createComment(long memberId, CommentInput commentInput) {
        Member member = memberRepository.findById(memberId).orElseThrow(DgsEntityNotFoundException::new);
        Post post = postRepository.findById(commentInput.getPostId()).orElseThrow(DgsEntityNotFoundException::new);
        Comment comment = commentInput.toComment(post, member);
        commentRepository.save(comment);
        return CommentOutput.from(comment);
    }

    public List<CommentOutput> findByMemberId(long id) {
        return commentRepository.findByMemberId(id).stream().map(CommentOutput::from).toList();
    }

    public List<CommentOutput> findByPostId(long id) {
        return commentRepository.findByPostId(id).stream().map(CommentOutput::from).toList();
    }

    public Comment findById(long id) {
        return commentRepository.findById(id).orElseThrow(DgsEntityNotFoundException::new);
    }

    public boolean deleteCommentByMemberIdAndId(long memberId, long id) {
        Member member = memberRepository.findById(memberId).orElseThrow(DgsEntityNotFoundException::new);
        Comment comment = commentRepository.findById(id).orElseThrow(DgsEntityNotFoundException::new);
        if (member.getId() != comment.getMember().getId()) {
            return false;
        }
        commentRepository.delete(comment);
        return true;
    }
}
