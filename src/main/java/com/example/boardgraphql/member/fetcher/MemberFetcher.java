package com.example.boardgraphql.member.fetcher;

import com.example.boardgraphql.comment.dto.output.CommentOutput;
import com.example.boardgraphql.member.dto.input.CreateMemberInput;
import com.example.boardgraphql.member.dto.input.LoginInput;
import com.example.boardgraphql.member.dto.output.MemberOutput;
import com.example.boardgraphql.member.dto.output.MyInfo;
import com.example.boardgraphql.member.service.MemberService;
import com.example.boardgraphql.post.dto.output.PostOutput;
import com.example.boardgraphql.security.CustomUserDetail;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.internal.DgsWebMvcRequestData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

@DgsComponent
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFetcher {


    private final MemberService memberService;

    @DgsData(parentType = "Post", field = "member")
    public MemberOutput memberByPost(DgsDataFetchingEnvironment dgs) {
        PostOutput postOutput = dgs.getSource();
        assert postOutput != null;
        return memberService.findById(postOutput);
    }

    @DgsData(parentType = "Comment", field = "member")
    public MemberOutput memberByComment(DgsDataFetchingEnvironment dgs) {
        CommentOutput commentOutput = dgs.getSource();
        assert commentOutput != null;
        return memberService.findById(commentOutput);
    }


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
    public String login(@InputArgument(name = "input") LoginInput loginInput,
                        DgsDataFetchingEnvironment dgs) {

        String token = memberService.login(loginInput);

        DgsWebMvcRequestData requestData = (DgsWebMvcRequestData) dgs.getDgsContext().getRequestData();
        assert requestData != null;
        ServletWebRequest webRequest = (ServletWebRequest) requestData.getWebRequest();
        assert webRequest != null;
        HttpServletResponse httpServletResponse = webRequest.getResponse();
        assert httpServletResponse != null;
        httpServletResponse.setHeader("Authorization", "Bearer " + token);

        return token;
    }


}
