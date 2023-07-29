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

    public static SocialDisasterDTO of (boolean trafficControl, boolean fireAlert, boolean collapse, boolean explosion, boolean trafficAccident, boolean envPollution, boolean energy, boolean communication, boolean medical, boolean waterAlert, boolean epidemic, boolean blackout, boolean gas, boolean missing, boolean traffic, boolean socialEtc) {
        return SocialDisasterDTO.builder()
                .trafficControl(trafficControl)
                .fireAlert(fireAlert)
                .collapse(collapse)
                .explosion(explosion)
                .trafficAccident(trafficAccident)
                .envPollution(envPollution)
                .energy(energy)
                .communication(communication)
                .medical(medical)
                .waterAlert(waterAlert)
                .epidemic(epidemic)
                .blackout(blackout)
                .gas(gas)
                .missing(missing)
                .traffic(traffic)
                .socialEtc(socialEtc)
                .build();
    }
}
