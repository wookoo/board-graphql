package com.example.boardgraphql.member.service;


import com.example.boardgraphql.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberId(String memberId);
}
