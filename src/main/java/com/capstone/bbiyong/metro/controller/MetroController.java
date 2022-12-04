package com.capstone.bbiyong.metro.controller;

import com.capstone.bbiyong.openapi.MetroOpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@EnableScheduling
@RestController
@RequestMapping("twitter")
@RequiredArgsConstructor
public class MetroController {

    private final MetroOpenAPI metroOpenAPI;

    @Scheduled(fixedDelay = 60000)  // 1분 간격
    @GetMapping("")
    @ResponseStatus(OK)
    public void callTwitterOpenAPI() {
        metroOpenAPI.callOpenAPI();
    }
}