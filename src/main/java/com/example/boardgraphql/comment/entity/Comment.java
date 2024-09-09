package com.example.boardgraphql.comment.entity;


import com.example.boardgraphql.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder(access = AccessLevel.PUBLIC)
    private Comment(String content, Member member) {
        this.content = content;
        this.member = member;
    }

}
