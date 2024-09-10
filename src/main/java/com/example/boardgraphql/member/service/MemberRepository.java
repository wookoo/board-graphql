package com.example.boardgraphql.member.service;


import com.example.boardgraphql.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.Optional;

@GraphQlRepository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);
}
