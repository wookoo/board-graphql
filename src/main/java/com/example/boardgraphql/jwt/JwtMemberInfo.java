package com.example.boardgraphql.jwt;

import com.example.boardgraphql.member.entity.Member;
import com.example.boardgraphql.member.entity.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PUBLIC)
public class JwtMemberInfo {

    private long id;
    private Role role;

    public static JwtMemberInfo from(Member member) {
        return JwtMemberInfo.builder().id(member.getId()).role(member.getRole()).build();
    }


}
