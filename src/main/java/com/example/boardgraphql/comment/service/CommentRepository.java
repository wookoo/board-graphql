package com.example.boardgraphql.comment.service;

import com.example.boardgraphql.comment.entity.Comment;
import com.example.boardgraphql.member.entity.Member;
import com.example.boardgraphql.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;

@GraphQlRepository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMember(Member member);

    List<Comment> findByPost(Post post);
}
