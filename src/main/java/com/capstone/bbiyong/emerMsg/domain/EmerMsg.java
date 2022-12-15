package com.capstone.bbiyong.emerMsg.domain;

import com.capstone.bbiyong.common.domain.DateTimeEntity;
import com.capstone.bbiyong.location.domain.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class EmerMsg extends DateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emer_msg_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long openapiId;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Builder
    public EmerMsg(Long openapiId, String message, Date startDate, Date endDate, Location location) {
        this.openapiId = openapiId;
        this.message = message;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }
}
