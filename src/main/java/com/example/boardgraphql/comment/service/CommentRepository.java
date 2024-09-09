package com.example.boardgraphql.comment.service;

import com.example.boardgraphql.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;

@GraphQlRepository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMemberId(long memberId);

    List<Comment> findByPostId(long postId);
}
