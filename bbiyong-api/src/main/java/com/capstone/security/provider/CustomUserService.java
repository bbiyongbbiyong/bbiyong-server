package com.capstone.security.provider;

import com.capstone.common.exception.EntityNotFoundException;
import com.capstone.common.exception.ErrorCode;
import com.capstone.member.domain.Member;
import com.capstone.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public SecurityUserDetails loadUserByUsername(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
        return new SecurityUserDetails(member);
    }

}
