package com.capstone.bbiyong.accident.domain;

import com.capstone.bbiyong.common.domain.DateTimeEntity;
import com.capstone.bbiyong.emerMsg.domain.EmerMsg;
import com.capstone.bbiyong.location.domain.Location;
import com.capstone.bbiyong.metro.domain.Metro;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class Accident extends DateTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "accident_id")
    private Long id;

    private Long openapiId;
    private String accidentType;
    private String accidentInfo;
    private String xMap;
    private String yMap;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Builder
    public Accident(Long openapiId, Date startDate, Date endDate, String accidentType, String accidentInfo, String xMap, String yMap, Location location) {
        this.openapiId = openapiId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accidentType = accidentType;
        this.accidentInfo = accidentInfo;
        this.xMap = xMap;
        this.yMap = yMap;
        this.location = location;
    }
}
