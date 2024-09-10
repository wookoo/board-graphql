package com.example.boardgraphql.member.controller;

import com.example.boardgraphql.member.service.MemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberController {
    private final MemberService memberService;


}
