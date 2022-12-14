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
    @Column(nullable = false)
    private Long id;

    @Column(name = "emer_msg_id", nullable = false)
    private Long emerMsgId;

    @Column(nullable = false)
    private String msg;

    @Column(nullable = false)
    private Date startDateTime;

    @Column(nullable = false)
    private Date endDateTime;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Builder
    public EmerMsg(Long emerMsgId, String msg, Date startDateTime, Date endDateTime) {
        this.emerMsgId = emerMsgId;
        this.msg = msg;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
