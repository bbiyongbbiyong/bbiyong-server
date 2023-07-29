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
public class NotificationListDTO {

    private NaturalDisasterDTO naturalDisaster;
    private SocialDisasterDTO socialDisaster;
    private SubwayDTO subwayInformation;
    private RoadControllerDTO roadControlInformation;

    public static NotificationListDTO of (NaturalDisasterDTO naturalDisaster, SocialDisasterDTO socialDisaster, SubwayDTO subwayInformation, RoadControllerDTO roadControlInformation) {
        return NotificationListDTO.builder()
                .naturalDisaster(naturalDisaster)
                .socialDisaster(socialDisaster)
                .subwayInformation(subwayInformation)
                .roadControlInformation(roadControlInformation)
                .build();
    }
}
