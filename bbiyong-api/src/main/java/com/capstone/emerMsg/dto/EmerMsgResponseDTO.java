package com.capstone.emerMsg.dto;

import com.capstone.emerMsg.domain.EmerMsg;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class EmerMsgResponseDTO {

    private Long locationId;

    private String locationName;

    private String accidentInfo;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy.MM.dd HH:mm",
            locale = "Asia/Seoul"
    )
    private Date startDate;

    public static EmerMsgResponseDTO from(EmerMsg emerMsg) {
        return EmerMsgResponseDTO.builder()
                .locationId(emerMsg.getLocation().getId())
                .locationName(emerMsg.getLocation().getName())
                .accidentInfo(emerMsg.getMessage())
                .startDate(emerMsg.getStartDate())
                .build();
    }
}
