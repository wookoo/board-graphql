package com.example.boardgraphql.comment.service;

import com.example.boardgraphql.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
