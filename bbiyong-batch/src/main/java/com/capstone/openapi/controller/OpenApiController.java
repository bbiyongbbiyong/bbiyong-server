package com.capstone.openapi.controller;

import com.capstone.openapi.EmerMsgOpenAPI;
import com.capstone.openapi.MetroOpenAPI;
import com.capstone.openapi.OpenAPI;
import com.capstone.common.dto.BasicResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

import static org.springframework.http.HttpStatus.OK;

@EnableScheduling
@RestController
@RequestMapping("parse")
@RequiredArgsConstructor
@Tag(name = "👀 OPEN API 파싱", description = "사건/사고, 재난문자, 지하철 지연 OPEN API")
public class OpenApiController {
    private final OpenAPI accidentOpenAPI;
    private final EmerMsgOpenAPI emerMsgOpenAPI;
    private final MetroOpenAPI metroOpenAPI;

    private final BasicResponse basicResponse = new BasicResponse();


    @Scheduled(fixedDelay = 60000)  // 1분 간격
    @GetMapping("/accident")
    @ResponseStatus(OK)
    @Operation(summary = "사건/사고 및 시위 API 자동 호출", description = "1분마다 사건/사고 및 시위 정보를 업데이트하여 DB에 저장합니다")
    public ResponseEntity<BasicResponse> callAccidentOpenAPI() throws IOException, ParseException, org.json.simple.parser.ParseException {
        accidentOpenAPI.call();
        return basicResponse.noContent();
    }

    @Scheduled(fixedDelay = 60000)  // 1분 간격
    @GetMapping("/emerMsg")
    @ResponseStatus(OK)
    @Operation(summary = "재난문자 API 자동 호출", description = "1분마다 재난문자 정보를 업데이트하여 DB에 저장합니다")
    public ResponseEntity<BasicResponse> callEmerMsgOpenAPI() throws IOException, ParseException {
        emerMsgOpenAPI.call();
        return basicResponse.noContent();
    }

    @Scheduled(fixedDelay = 60000)  // 1분 간격
    @GetMapping("/metro")
    @ResponseStatus(OK)
    @Operation(summary = "지하철 지연 API 자동 호출", description = "1분마다 지하철 지연 관련 정보를 업데이트하여 DB에 저장합니다")
    public ResponseEntity<BasicResponse> callTwitterOpenAPI() {
        metroOpenAPI.callOpenAPI();
        return basicResponse.noContent();
    }

}
