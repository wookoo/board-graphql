package com.example.boardgraphql.member.dto.input;

import com.example.boardgraphql.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberInput {


    private String memberId;
    private String name;
    private String password;
    private String nickName;
    private String email;

    public Member toMember() {

        return Member.builder()
                .memberId(memberId)
                .name(name)
                .password(password)
                .nickName(nickName)
                .email(email)
                .build();
    }
}
