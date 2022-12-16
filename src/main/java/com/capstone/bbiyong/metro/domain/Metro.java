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

    @Column(nullable = false)
    private Long openapiId;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;


    @Builder
    public Metro(Long openapiId, String text, LocalDateTime startDate, LocalDateTime endDate) {
        this.openapiId = openapiId;
        this.text = text;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
