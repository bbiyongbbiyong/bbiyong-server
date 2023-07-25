package com.capstone.metro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "stationid")
    private Long id;

    private String name;

    private String line;

    public Station(String name, String line) {
        this.name = name;
        this.line = line;
    }
}
