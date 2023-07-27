package com.capstone.emerMsg.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class Disaster {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "disaster_id")
    private Long id;

    private String type;
    private String krTopic;
    private String enTopic;

    public Disaster(String type, String krTopic, String enTopic) {
        this.type = type;
        this.krTopic = krTopic;
        this.enTopic = enTopic;
    }
}
