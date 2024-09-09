package com.example.boardgraphql.post.dto.input;

import com.example.boardgraphql.member.entity.Member;
import com.example.boardgraphql.post.entity.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostInput {
    private String title;
    private String content;

    public Post toPost(Member member) {
        return Post.builder().title(title)
                .content(content)
                .member(member)
                .build();
    }
}
