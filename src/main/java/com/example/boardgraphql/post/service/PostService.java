package com.example.boardgraphql.post.service;

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

    public List<PostOutput> findByMemberId(long id) {

        return postRepository.findByMemberId(id).stream().map(PostOutput::from).toList();
    }

    public PostOutput findPostById(long id){
        return PostOutput.from(postRepository.findById(id).get());
    }
}
