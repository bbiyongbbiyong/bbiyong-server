package com.capstone.bbiyong.accident.controller;

import com.capstone.bbiyong.accident.service.AccidentService;
import com.capstone.bbiyong.openapi.OpenAPI;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("accident")
@RequiredArgsConstructor
public class AccidentController {

    private final AccidentService accidentService;
    private final OpenAPI accidentOpenAPI;

    @Scheduled(fixedDelay = 60000)  // 1분 간격
    @GetMapping("")
    @ResponseStatus(OK)
    @Operation(summary = "사건/사고 및 시위 API 자동 호출", description = "1분마다 사건/사고 및 시위 정보를 업데이트하여 DB에 저장합니다")
    public void callAccidentOpenAPI() throws IOException, ParseException {
        accidentOpenAPI.call();
    }
}
