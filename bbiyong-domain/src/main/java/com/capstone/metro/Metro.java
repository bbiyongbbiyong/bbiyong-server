package com.capstone.metro;

import com.capstone.common.DateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class Metro extends DateTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
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
