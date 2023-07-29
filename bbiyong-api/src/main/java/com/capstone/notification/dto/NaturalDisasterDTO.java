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

    public static NaturalDisasterDTO of (boolean typhoon, boolean dry,boolean forestFire, boolean landslide, boolean flood, boolean downpour, boolean heatWave, boolean fog, boolean windWave, boolean fineDust, boolean springTide, boolean drought, boolean heavySnow, boolean tsunami, boolean earthquake, boolean coldWave, boolean yellowDust, boolean gale, boolean naturalEtc) {
        return NaturalDisasterDTO.builder()
                .typhoon(typhoon)
                .dry(dry)
                .forestFires(forestFire)
                .landslide(landslide)
                .flood(flood)
                .downpour(downpour)
                .heatWave(heatWave)
                .fog(fog)
                .windWave(windWave)
                .fineDust(fineDust)
                .springTide(springTide)
                .drought(drought)
                .heavySnow(heavySnow)
                .tsunami(tsunami)
                .earthquake(earthquake)
                .coldWave(coldWave)
                .yellowDust(yellowDust)
                .gale(gale)
                .naturalEtc(naturalEtc)
                .build();
    }
}
