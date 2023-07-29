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
public class NaturalDisasterDTO {

    private boolean typhoon;
    private boolean dry;
    private boolean forestFires;
    private boolean landslide;
    private boolean flood;
    private boolean downpour;
    private boolean heatWave;
    private boolean fog;
    private boolean windWave;
    private boolean fineDust;
    private boolean springTide;
    private boolean drought;
    private boolean heavySnow;
    private boolean tsunami;
    private boolean earthquake;
    private boolean coldWave;
    private boolean yellowDust;
    private boolean gale;
    private boolean naturalEtc;

}
