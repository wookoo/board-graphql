package com.example.boardgraphql.member.service;


import com.example.boardgraphql.comment.dto.output.CommentOutput;
import com.example.boardgraphql.jwt.JwtMemberInfo;
import com.example.boardgraphql.jwt.JwtProvider;
import com.example.boardgraphql.member.dto.input.CreateMemberInput;
import com.example.boardgraphql.member.dto.input.LoginInput;
import com.example.boardgraphql.member.dto.output.MemberOutput;
import com.example.boardgraphql.member.dto.output.MyInfo;
import com.example.boardgraphql.member.entity.Member;
import com.example.boardgraphql.post.dto.output.PostOutput;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    public MyInfo createMember(CreateMemberInput createMemberInput) {
        Member member = createMemberInput.toMember();

        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        memberRepository.save(member);
        return MyInfo.from(member);

    }

    public String login(LoginInput loginInput) {
        Member member = memberRepository.findByMemberId(loginInput.getMemberId()).orElseThrow(
                DgsEntityNotFoundException::new
        );
        JwtMemberInfo jwtMemberInfo = JwtMemberInfo.from(member);
        if (passwordEncoder.matches(loginInput.getPassword(), member.getPassword())) {
            return jwtProvider.createToken(jwtMemberInfo);
        }
        return null;
    }

    public MyInfo me(long id) {
        Member member = memberRepository.findById(id).orElseThrow(DgsEntityNotFoundException::new);
        return MyInfo.from(member);
    }

    public MemberOutput findById(PostOutput postOutput) {
        Member member = memberRepository.findById(postOutput.getMemberId()).orElseThrow(
                DgsEntityNotFoundException::new
        );
        return MemberOutput.from(member);
    }

    public MemberOutput findById(CommentOutput commentOutput) {
        Member member = memberRepository.findById(commentOutput.getMemberId()).orElseThrow(
                DgsEntityNotFoundException::new
        );
        return MemberOutput.from(member);
    }
}
