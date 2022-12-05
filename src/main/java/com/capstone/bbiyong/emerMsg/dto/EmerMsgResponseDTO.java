package com.capstone.bbiyong.emerMsg.dto;

import com.capstone.bbiyong.emerMsg.domain.EmerMsg;
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

    private String locationName;

    private String msg;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy.MM.dd HH:mm",
            locale = "Asia/Seoul"
    )
    private Date startDateTime;

    public static EmerMsgResponseDTO from(EmerMsg emerMsg) {
        return EmerMsgResponseDTO.builder()
                .locationName(emerMsg.getLocationName())
                .msg(emerMsg.getMsg())
                .startDateTime(emerMsg.getStartDateTime())
                .build();
    }
}
