package com.capstone.bbiyong.emerMsg.controller;

import com.capstone.bbiyong.openapi.EmerMsgOpenAPI;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

@EnableScheduling
@RestController
@RequestMapping("emerMsg")
@RequiredArgsConstructor
public class EmerMsgController {
    private final EmerMsgOpenAPI emerMsgOpenAPI;

    @Scheduled(fixedDelay = 60000)  // 1분 간격
    @GetMapping("")
    @ResponseStatus(OK)
    public void callEmerMsgOpenAPI() throws IOException, ParseException, java.text.ParseException {
        emerMsgOpenAPI.parseAndSave();
    }
}