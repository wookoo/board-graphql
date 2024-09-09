package com.example.boardgraphql.member.fetcher;

import com.example.boardgraphql.member.dto.input.CreateMemberInput;
import com.example.boardgraphql.member.dto.input.LoginInput;
import com.example.boardgraphql.member.dto.output.MyInfo;
import com.example.boardgraphql.member.service.MemberService;
import com.example.boardgraphql.security.CustomUserDetail;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@DgsComponent
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFetcher {


    private final MemberService memberService;

    @DgsMutation
    public MyInfo createMember(@InputArgument(name = "input") CreateMemberInput createMemberInput) {
        return memberService.createMember(createMemberInput);
    }


    @Secured("ROLE_NORMAL")
    @DgsQuery
    public MyInfo me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        long id = userDetails.getId();
        return memberService.me(id);
    }

    @DgsQuery
    public String login(@InputArgument(name = "input") LoginInput loginInput) {
        return memberService.login(loginInput);
    }


}
