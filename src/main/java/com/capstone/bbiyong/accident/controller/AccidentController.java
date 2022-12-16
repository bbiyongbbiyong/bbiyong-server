package com.capstone.bbiyong.accident.controller;

import com.capstone.bbiyong.accident.dto.AccidentResponseDTO;
import com.capstone.bbiyong.accident.service.AccidentService;
import com.capstone.bbiyong.common.dto.BasicResponse;
import com.capstone.bbiyong.openapi.OpenAPI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

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
}
