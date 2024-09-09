package com.example.boardgraphql.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;

    @Setter
    private String password;

    private String nickName;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder(access = AccessLevel.PUBLIC)
    private Member(String memberId, String password, String nickName, String name, String email) {
        this.memberId = memberId;
        this.password = password;
        this.nickName = nickName;
        this.name = name;
        this.email = email;
        this.role = Role.NORMAL;
    }


}
