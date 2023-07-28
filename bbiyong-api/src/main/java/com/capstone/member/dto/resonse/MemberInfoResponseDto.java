package com.capstone.member.dto.resonse;

import com.capstone.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberInfoResponseDto {

    private final Long userId;

    private final String username;

    public static MemberInfoResponseDto of(Member member) {
        return MemberInfoResponseDto.builder()
                .userId(member.getId())
                .username(member.getUsername())
                .build();
    }

}
