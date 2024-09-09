package com.example.boardgraphql.comment.dto.input;

import com.example.boardgraphql.comment.entity.Comment;
import com.example.boardgraphql.member.entity.Member;
import com.example.boardgraphql.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentInput {
    private String content;
    private long postId;

    public Comment toComment(Post post, Member member) {
        return Comment.builder().content(content).member(member).post(post).build();
    }
}
