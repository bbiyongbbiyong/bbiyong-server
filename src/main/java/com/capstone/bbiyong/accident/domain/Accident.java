package com.capstone.bbiyong.accident.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class Accident {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "accident_id")
    private Long id;

    private Integer accId;

    private Date startDate;
    private Date endDate;

    private String accidentType;
    private String accidentInfo;

    private String xMap;
    private String yMap;

    @Builder
    public Accident(Integer accId, Date startDate, Date endDate, String accidentType, String accidentInfo, String xMap, String yMap) {
        this.accId = accId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accidentType = accidentType;
        this.accidentInfo = accidentInfo;
        this.xMap = xMap;
        this.yMap = yMap;
    }
}
