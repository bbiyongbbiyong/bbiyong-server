package com.capstone.accident.controller;

import com.capstone.accident.service.AccidentService;
import com.capstone.common.dto.BasicResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;


@EnableScheduling
@RestController
@RequestMapping("accident")
@RequiredArgsConstructor
@Tag(name = "🚗️ Accident ", description = "사건/사고 API")
public class AccidentController {

    private final AccidentService accidentService;
    private final BasicResponse basicResponse = new BasicResponse();


    @GetMapping("/{locationId}")
    @Operation(summary = "지역 별 사건/사고 조회", description = "지역 별 사건/사고를 조회합니다")
    public ResponseEntity<BasicResponse> getAccident(@PathVariable("locationId") Long locationId) {
        return basicResponse.ok(
                accidentService.getAccident(locationId)
        );
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @DeleteMapping("/delete")
    @Operation(summary = "일주일 지난 사건/사고 삭제", description = "종료된지 일주일이 지난 사건/사고를 DB에서 삭제합니다")
    public ResponseEntity<BasicResponse> deleteAccidents () {
        accidentService.deleteAccidents();
        return basicResponse.noContent();
    }

    @GetMapping("/most")
    @Operation(summary = "최빈값 조회", description = "지난 일주일 간 서울 전역에서 가장 많이 발생한 사고 유형 및 해당 사고 유형 최다 발생 구를 조회합니다.")
    public ResponseEntity<BasicResponse> getMostAccident() {
        return basicResponse.ok(
                accidentService.getMostAccident()
        );
    }
}
