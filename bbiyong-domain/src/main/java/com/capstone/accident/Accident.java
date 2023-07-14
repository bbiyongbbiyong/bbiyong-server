package com.capstone.accident;

import com.capstone.common.DateTimeEntity;
import com.capstone.location.Location;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

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
    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Builder
    public Accident(Long openapiId, Date startDate, Date endDate, String accidentType, String accidentInfo, Location location) {
        this.openapiId = openapiId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accidentType = accidentType;
        this.accidentInfo = accidentInfo;
        this.location = location;
    }
}
