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
public class SubwayDTO {

    private boolean line1;
    private boolean line2;
    private boolean line3;
    private boolean line4;
    private boolean line5;
    private boolean line6;
    private boolean line7;
    private boolean line8;
    private boolean line9;
    private boolean incheonLine1;
    private boolean incheonLine2;
    private boolean westcoastLine;
    private boolean geongangLine;
    private boolean airportLine;
    private boolean bundangLine;
    private boolean dxLine;
    private boolean uiLine;
    private boolean yonginLine;
    private boolean ulrtLine;
    private boolean gimpoLine;
    private boolean sillimLine;
    private boolean geongchunLine;
    private boolean gyoungiLine;
    private boolean lineEtc;

    public static SubwayDTO of (boolean line1, boolean line2, boolean line3, boolean line4, boolean line5, boolean line6, boolean line7, boolean line8, boolean line9, boolean incheonLine1, boolean incheonLine2, boolean westcoastLine, boolean geongangLine, boolean airportLine, boolean bundangLin, boolean dxLine, boolean uiLine, boolean yonginLine, boolean ulrtLine, boolean gimpoLine, boolean sillimLine, boolean geongchunLine, boolean gyoungiLine, boolean lineEtc) {
        return SubwayDTO.builder()
                .line1(line1)
                .line2(line2)
                .line3(line3)
                .line4(line4)
                .line5(line5)
                .line6(line6)
                .line7(line7)
                .line8(line8)
                .line9(line9)
                .incheonLine1(incheonLine1)
                .incheonLine2(incheonLine2)
                .westcoastLine(westcoastLine)
                .geongangLine(geongangLine)
                .airportLine(airportLine)
                .bundangLine(bundangLin)
                .dxLine(dxLine)
                .uiLine(uiLine)
                .yonginLine(yonginLine)
                .ulrtLine(ulrtLine)
                .gimpoLine(gimpoLine)
                .sillimLine(sillimLine)
                .geongchunLine(geongchunLine)
                .gyoungiLine(gyoungiLine)
                .lineEtc(lineEtc)
                .build();
    }
}
