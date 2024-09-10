package com.example.boardgraphql.post.service;

import com.example.boardgraphql.comment.entity.Comment;
import com.example.boardgraphql.comment.service.CommentRepository;
import com.example.boardgraphql.member.entity.Member;
import com.example.boardgraphql.member.service.MemberRepository;
import com.example.boardgraphql.post.dto.input.PostInput;
import com.example.boardgraphql.post.dto.output.PostOutput;
import com.example.boardgraphql.post.entity.Post;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PostService {
    private final PostRepository postRepository;

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    public List<PostOutput> findByMemberId(long id) {

        return postRepository.findByMemberId(id).stream().map(PostOutput::from).toList();
    }

    public PostOutput findPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                DgsEntityNotFoundException::new
        );
        return PostOutput.from(post);
    }

    public PostOutput createPost(long memberId, PostInput postInput) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                DgsEntityNotFoundException::new
        );
        Post post = postInput.toPost(member);
        postRepository.save(post);
        return PostOutput.from(post);
    }

    public PostOutput findByCommentId(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                DgsEntityNotFoundException::new
        );
        Post post = comment.getPost();
        return PostOutput.from(post);
    }
}
