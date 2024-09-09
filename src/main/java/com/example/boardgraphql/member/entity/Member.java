package com.example.boardgraphql.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String memberId;

    @Setter
    public String password;

    public String nickName;

    public String name;

    public String email;


    @Builder(access = AccessLevel.PUBLIC)
    private Member(String memberId, String password, String nickName, String name, String email) {
        this.memberId = memberId;
        this.password = password;
        this.nickName = nickName;
        this.name = name;
        this.email = email;
    }


}
