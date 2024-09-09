package com.example.boardgraphql.post.service;

import com.example.boardgraphql.member.entity.Member;
import com.example.boardgraphql.member.service.MemberService;
import com.example.boardgraphql.post.dto.input.PostInput;
import com.example.boardgraphql.post.dto.output.PostOutput;
import com.example.boardgraphql.post.entity.Post;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PostService {
    private final PostRepository postRepository;

    private final MemberService memberService;

    public List<PostOutput> findByMemberId(long id) {

        return postRepository.findByMemberId(id).stream().map(PostOutput::from).toList();
    }

    public PostOutput findPostById(long id) {
        return PostOutput.from(postRepository.findById(id).get());
    }

    public PostOutput createPost(long memberId, PostInput postInput) {
        Member member = memberService.findById(memberId);
        Post post = postInput.toPost(member);
        postRepository.save(post);
        return PostOutput.from(post);
    }

    public Post findById(long postId) {
        return postRepository.findById(postId).get();
    }
}
