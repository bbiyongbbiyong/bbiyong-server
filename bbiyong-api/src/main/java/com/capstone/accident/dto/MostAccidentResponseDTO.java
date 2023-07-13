package com.capstone.accident.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class MostAccidentResponseDTO {

    private String accidentType;

    private Long locationId;

    public static MostAccidentResponseDTO from(String accidentType, Long locationId) {
        return MostAccidentResponseDTO.builder()
                .accidentType(accidentType)
                .locationId(locationId)
                .build();
    }
}
