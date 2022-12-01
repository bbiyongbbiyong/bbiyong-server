package com.capstone.bbiyong.data.Twitter.domain;

import com.capstone.bbiyong.common.domain.DateTimeEntity;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Twitter extends DateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "twitter_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Builder
    public Twitter(String text) {
        this.text = text;
    }
}
