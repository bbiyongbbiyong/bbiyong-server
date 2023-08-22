package com.capstone.notification.domain;

import com.capstone.common.DateTimeEntity;
import com.capstone.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class FcmToken extends DateTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fcm_token_id", nullable = false)
    private Long id;

    private String token;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public FcmToken(String token, Member member) {
        this.token = token;
        this.member = member;
    }
}
