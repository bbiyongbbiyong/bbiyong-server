package com.capstone.bbiyong.accident.dto;

import com.capstone.bbiyong.accident.domain.Accident;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class AccidentResponseDTO {

    private Long accidentId;

    private Date startDate;
    private Date endDate;

    private String accidentType;
    private String accidentInfo;

    private String xMap;
    private String yMap;

    public static AccidentResponseDTO from(Accident accident) {
        return AccidentResponseDTO.builder()
                .accidentId(accident.getId())
                .startDate(accident.getStartDate())
                .endDate(accident.getEndDate())
                .accidentType(accident.getAccidentType())
                .accidentInfo(accident.getAccidentInfo())
                .xMap(accident.getXMap())
                .yMap(accident.getYMap())
                .build();
    }

}
