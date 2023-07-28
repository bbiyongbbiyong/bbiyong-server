package com.capstone.member.controller;

import com.capstone.common.dto.BasicResponse;
import com.capstone.member.dto.request.JoinRequestDto;
import com.capstone.member.dto.request.LoginRequestDto;
import com.capstone.member.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "🔑 인증", description = "회원가입, 로그인")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final BasicResponse basicResponse = new BasicResponse();

    @PostMapping("/join")
    @Operation(summary = "회원 가입")
    public ResponseEntity<BasicResponse> join(@RequestBody JoinRequestDto joinRequestDto) {
        var memberInfoResponseDto = authService.join(joinRequestDto.getUsername(), joinRequestDto.getPassword());
        return basicResponse.ok(memberInfoResponseDto.getUsername());
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<BasicResponse> login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = authService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        return basicResponse.ok(token);
    }

}
