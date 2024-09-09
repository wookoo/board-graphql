package com.example.boardgraphql.post.entity;

import com.example.boardgraphql.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder(access = AccessLevel.PUBLIC)
    private Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
