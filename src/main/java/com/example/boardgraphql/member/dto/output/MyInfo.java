package com.example.boardgraphql.member.dto.output;


import com.example.boardgraphql.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder(access = AccessLevel.PRIVATE)
public class MyInfo {

    private long id;
    private String nickName;
    private String name;
    private String email;

    public static MyInfo from(Member member) {
        return MyInfo.builder()
                .id(member.getId())
                .nickName(member.getNickName())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }


}
