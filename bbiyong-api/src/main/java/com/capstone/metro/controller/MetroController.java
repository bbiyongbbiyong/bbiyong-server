package com.capstone.metro.controller;

import com.capstone.common.dto.BasicResponse;
import com.capstone.metro.service.MetroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@EnableScheduling
@RestController
@RequestMapping("metro")
@RequiredArgsConstructor
@Tag(name = "🚆 Metro", description = "지하철 지연 정보 API")
public class MetroController {

    private final MetroService metroService;
    private final BasicResponse basicResponse = new BasicResponse();

    @GetMapping("/view")
    @Operation(summary = "지하철 지연 정보 조회", description = "지하철 지연 정보를 조회합니다")
    public ResponseEntity<BasicResponse> getMetro() {
        return basicResponse.ok(
                metroService.getMetro(LocalDateTime.now())
        );
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @DeleteMapping("/delete")
    @Operation(summary = "일주일 지난 지하철 지연 정보 삭제", description = "종료된지 일주일이 지난 지하철 지연 정보를 DB에서 삭제합니다")
    public ResponseEntity<BasicResponse> deleteMetro () {
        metroService.deleteMetros();
        return basicResponse.noContent();
    }
}