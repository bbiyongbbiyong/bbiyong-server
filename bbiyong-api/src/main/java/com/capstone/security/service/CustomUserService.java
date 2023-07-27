package com.capstone.security.service;

import com.capstone.common.exception.BusinessException;
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
        try {
            return memberRepository.findByUsername(username)
                    .map(MemberDto::of)
                    .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
