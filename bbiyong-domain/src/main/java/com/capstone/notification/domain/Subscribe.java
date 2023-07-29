package com.capstone.notification.domain;

import com.capstone.common.DateTimeEntity;
import com.capstone.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class Subscribe extends DateTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "subscribe_id", nullable = false)
    private Long id;

    private String type;

    private String topic;

    private boolean isSubscribe;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Subscribe(String type, String topic, boolean isSubscribe, Member member) {
        this.type = type;
        this.topic = topic;
        this.isSubscribe = isSubscribe;
        this.member = member;
    }

    public void update(boolean isSubscribe) {
        this.isSubscribe = isSubscribe;
    }
}
