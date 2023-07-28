package com.capstone.member.service;

import com.capstone.common.exception.BusinessException;
import com.capstone.common.exception.ErrorCode;
import com.capstone.member.domain.Member;
import com.capstone.member.dto.resonse.MemberInfoResponseDto;
import com.capstone.member.repository.MemberRepository;
import com.capstone.security.service.CustomUserService;
import com.capstone.security.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomUserService customUserService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;

    @Transactional
    public MemberInfoResponseDto join(String username, String password) {
        validateUnique(username);

        var member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        var savedMember = memberRepository.save(member);

        return MemberInfoResponseDto.of(savedMember);
    }

    public String login(String username, String password) {
        var userDto = customUserService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, userDto.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);
        }

        return JwtTokenUtils.generateToken(username, secretKey, expiredTimeMs);
    }

    private void validateUnique(String username) {
        if (memberRepository.findByUsername(username).isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATED_USERNAME);
        }
    }

}
