package com.example.boardgraphql.post.dto.output;

import com.example.boardgraphql.post.entity.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "from", access = AccessLevel.PRIVATE)
public class PostOutput {

    private long id;
    private String title;
    private String content;
    private long memberId;

    public static PostOutput from(Post post) {
        return PostOutput.from(post.getId(), post.getTitle(), post.getContent(), post.getMember().getId());
    }


}
