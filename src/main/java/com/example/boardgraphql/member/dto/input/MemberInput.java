package com.example.boardgraphql.member.dto.input;


import com.example.boardgraphql.member.entity.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class MemberInput {

    private String memberId;
    private String password;
    private String nickName;
    private String name;
    private String email;

    public Member toMember(){

        return Member.builder()
                .memberId(memberId)
                .password(password)
                .nickName(nickName)
                .name(name)
                .email(email)
                .build();
    }
}
