package com.capstone.member.dto.resonse;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class MemberInfoResponseDto {

    private final Long userId;

    private final String username;

}
