package com.capstone.bbiyong.metro.controller;

import com.capstone.bbiyong.metro.dto.MetroResponseDTO;
import com.capstone.bbiyong.metro.service.MetroService;
import com.capstone.bbiyong.openapi.MetroOpenAPI;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@EnableScheduling
@RestController
@RequestMapping("twitter")
@RequiredArgsConstructor
public class MetroController {

    private final MetroOpenAPI metroOpenAPI;
    private final MetroService metroService;

    @Scheduled(fixedDelay = 60000)  // 1분 간격
    @GetMapping("")
    @ResponseStatus(OK)
    @Operation(summary = "지하철 지연 API 자동 호출", description = "1분마다 지하철 지연 관련 정보를 업데이트하여 DB에 저장합니다")
    public void callTwitterOpenAPI() {
        metroOpenAPI.callOpenAPI();
    }

    @GetMapping("/view")
    @Operation(summary = "지하철 지연 정보 조회", description = "지하철 지연 정보를 조회합니다")
    public List<MetroResponseDTO> getMetro() {
        return metroService.getMetro(LocalDateTime.now());
    }
}