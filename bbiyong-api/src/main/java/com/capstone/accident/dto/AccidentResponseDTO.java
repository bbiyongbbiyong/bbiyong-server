package com.capstone.accident.dto;

import com.capstone.accident.domain.Accident;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class AccidentResponseDTO {

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy.MM.dd HH:mm",
            timezone = "GMT+9"
    )
    private Date startDate;

    private String accidentType;
    private String accidentTopic;
    private String accidentInfo;

    private Long locationId;

    public static AccidentResponseDTO from(Accident accident) {
        return AccidentResponseDTO.builder()
                .startDate(accident.getStartDate())
                .accidentType(accident.getAccidentType())
                .accidentTopic(accident.getAccidentTopic())
                .accidentInfo(accident.getAccidentInfo())
                .locationId(accident.getLocation().getId())
                .build();
    }

}
