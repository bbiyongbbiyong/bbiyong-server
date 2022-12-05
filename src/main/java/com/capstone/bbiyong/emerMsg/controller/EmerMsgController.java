package com.capstone.bbiyong.emerMsg.controller;

import com.capstone.bbiyong.emerMsg.dto.EmerMsgResponseDTO;
import com.capstone.bbiyong.emerMsg.service.EmerMsgService;
import com.capstone.bbiyong.openapi.EmerMsgOpenAPI;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@EnableScheduling
@RestController
@RequestMapping("emerMsg")
@RequiredArgsConstructor
public class EmerMsgController {
    private final EmerMsgOpenAPI emerMsgOpenAPI;
    private final EmerMsgService emerMsgService;

    @Scheduled(fixedDelay = 60000)  // 1분 간격
    @GetMapping("")
    @ResponseStatus(OK)
    @Operation(summary = "재난문자 API 자동 호출", description = "1분마다 재난문자 정보를 업데이트하여 DB에 저장합니다")
    public void callEmerMsgOpenAPI() throws IOException, ParseException, java.text.ParseException {
        emerMsgOpenAPI.parseAndSave();
    }

    @GetMapping("/{locationId}")
    @Operation(summary = "지역 별 재난문자 조회", description = "지역 별 발송된 재난문자를 조회합니다")
    public List<EmerMsgResponseDTO> getEmerMsgs(@PathVariable("locationId") Integer locationId) {
        return emerMsgService.getEmerMsg(locationId);
    }
}