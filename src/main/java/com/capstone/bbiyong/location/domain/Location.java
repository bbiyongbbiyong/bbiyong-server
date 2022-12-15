package com.capstone.bbiyong.location.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "location_id")
    private Long id;

    private String name;

    public Location(String name) {
        this.name = name;
    }

}
