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
    private boolean linegeongangLine1;
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

}
