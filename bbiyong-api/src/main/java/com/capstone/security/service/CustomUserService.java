package com.capstone.security.service;

import com.capstone.common.exception.EntityNotFoundException;
import com.capstone.common.exception.ErrorCode;
import com.capstone.member.dto.MemberDto;
import com.capstone.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public MemberDto loadUserByUsername(String username) {
        return memberRepository.findByUsername(username)
                .map(MemberDto::of)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

}
