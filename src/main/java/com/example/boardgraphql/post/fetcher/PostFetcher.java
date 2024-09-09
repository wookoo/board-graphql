package com.example.boardgraphql.post.fetcher;


import com.example.boardgraphql.post.dto.input.PostInput;
import com.example.boardgraphql.post.dto.output.PostOutput;
import com.example.boardgraphql.post.service.PostService;
import com.example.boardgraphql.security.CustomUserDetail;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@DgsComponent
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PostFetcher {
    private final PostService postService;


    @Secured("ROLE_NORMAL")
    @DgsMutation
    public PostOutput createPost(@InputArgument(name = "input") PostInput postInput) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        long memberId = userDetails.getId();
        return postService.createPost(memberId, postInput);
    }

}
