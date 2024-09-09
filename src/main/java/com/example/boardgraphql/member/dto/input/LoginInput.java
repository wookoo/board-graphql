package com.example.boardgraphql.member.dto.input;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginInput {

    private String memberId;
    private String password;
}
