package com.capstone.analytics.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class ChartDataResponseDTO {
    private int emergencyMsgYdy;
    private int emergencyMsgTdy;

    private int metroYdy;
    private int metroTdy;

    private int accidentYdy;
    private int accidentTdy;

    public static ChartDataResponseDTO from (int emergencyMsgYdy, int emergencyMsgTdy, int metroYdy, int metroTdy, int accidentYdy, int accidentTdy) {
        return ChartDataResponseDTO.builder()
                .emergencyMsgYdy(emergencyMsgYdy)
                .emergencyMsgTdy(emergencyMsgTdy)
                .metroYdy(metroYdy)
                .metroTdy(metroTdy)
                .accidentYdy(accidentYdy)
                .accidentTdy(accidentTdy)
                .build();
    }
}
