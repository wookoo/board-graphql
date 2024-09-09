package com.example.boardgraphql.post.entity;

import com.example.boardgraphql.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.graphql.data.GraphQlRepository;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;




}
