package com.example.boardgraphql.comment.dto.output;


import com.example.boardgraphql.comment.entity.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CommentOutput {

    private long id;
    private String content;
    private long memberId;

    public static CommentOutput from(Comment comment) {
        return CommentOutput.builder().id(comment.getId()).content(comment.getContent()).memberId(comment.getMember().getId()).build();
    }
}
