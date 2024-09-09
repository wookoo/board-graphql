package com.example.boardgraphql.member.fetcher;

import com.example.boardgraphql.member.dto.input.CreateMemberInput;
import com.example.boardgraphql.member.dto.output.MyInfo;
import com.example.boardgraphql.member.service.MemberService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFetcher {


    private final MemberService memberService;

    @DgsMutation
    public MyInfo createMember(@InputArgument(name = "input") CreateMemberInput createMemberInput) {
        return memberService.createMember(createMemberInput);
    }


}
