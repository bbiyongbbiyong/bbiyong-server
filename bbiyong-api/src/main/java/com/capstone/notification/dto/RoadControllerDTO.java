package com.capstone.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = PRIVATE)
public class RoadControllerDTO {

    private boolean roadAccident;
    private boolean roadWorks;
    private boolean rallyEvent;
    private boolean roadEtc;

    public static RoadControllerDTO of (boolean roadAccident, boolean roadWorks, boolean rallyEvent, boolean roadEtc) {
        return RoadControllerDTO.builder()
                .roadAccident(roadAccident)
                .roadWorks(roadWorks)
                .rallyEvent(rallyEvent)
                .roadEtc(roadEtc)
                .build();
    }
}
