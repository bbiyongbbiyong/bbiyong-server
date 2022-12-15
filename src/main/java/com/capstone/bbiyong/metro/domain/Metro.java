package com.capstone.bbiyong.metro.domain;

import com.capstone.bbiyong.common.domain.DateTimeEntity;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Metro extends DateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(name = "tweet_id", nullable = false)
    private Long tweetId;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;


    @Builder
    public Metro(Long tweetId, String text, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.tweetId = tweetId;
        this.text = text;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
