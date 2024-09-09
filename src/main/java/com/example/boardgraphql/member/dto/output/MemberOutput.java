package com.example.boardgraphql.member.dto.output;


import com.example.boardgraphql.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class MemberOutput {

    private long id;
    private String nickName;

    public static MemberOutput from(Member member) {

        return MemberOutput.builder()
                .id(member.getId())
                .nickName(member.getNickName())
                .build();
    }
}
