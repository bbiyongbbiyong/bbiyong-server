package com.capstone.analytics.controller;

import com.capstone.analytics.service.AnalyticsService;
import com.capstone.common.dto.BasicResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("analytics")
@RequiredArgsConstructor
@Tag(name = "📊 Analytics ", description = "데이터 분석 API")
public class AnalyticsController {

    private final AnalyticsService analyticsService;
    private final BasicResponse basicResponse = new BasicResponse();

    @GetMapping("")
    @Operation(summary = "홈 화면에 필요한 데이터 분석", description = "홈 화면 차트에 필요한 데이터를 분석합니다.")
    public ResponseEntity<BasicResponse> getAnalyzedData() {
        return basicResponse.ok(
                analyticsService.getAnalyzedData()
        );
    }
}
