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
public class SocialDisasterDTO {

    private boolean trafficControl;
    private boolean fireAlert;
    private boolean collapse;
    private boolean explosion;
    private boolean trafficAccident;
    private boolean envPollution;
    private boolean energy;
    private boolean communication;
    private boolean medical;
    private boolean waterAlert;
    private boolean epidemic;
    private boolean blackout;
    private boolean gas;
    private boolean missing;
    private boolean traffic;
    private boolean socialEtc;

}
