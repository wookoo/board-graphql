package com.example.boardgraphql.member.fetcher;

import com.example.boardgraphql.member.service.MemberService;
import com.netflix.graphql.dgs.DgsComponent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFetcher {


    private final MemberService memberService;

}
