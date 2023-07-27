package com.capstone.member.controller;

import com.capstone.common.dto.BasicResponse;
import com.capstone.member.dto.request.JoinRequestDto;
import com.capstone.member.dto.request.LoginRequestDto;
import com.capstone.member.dto.resonse.MemberInfoResponseDto;
import com.capstone.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final BasicResponse basicResponse = new BasicResponse();

    @PostMapping("/join")
    public ResponseEntity<BasicResponse> join(@RequestBody JoinRequestDto joinRequestDto) {
        var memberDto = authService.join(joinRequestDto.getUsername(), joinRequestDto.getPassword());
        return basicResponse.ok(
                MemberInfoResponseDto.builder()
                        .userId(memberDto.getId())
                        .username(memberDto.getUsername())
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<BasicResponse> login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = authService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        return basicResponse.ok(token);
    }

}
