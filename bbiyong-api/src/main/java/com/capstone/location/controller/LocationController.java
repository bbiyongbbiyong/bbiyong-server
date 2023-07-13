package com.capstone.location.controller;

import com.capstone.common.dto.BasicResponse;
import com.capstone.location.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("location")
@Tag(name = "🔴 Location Information", description = "지역 별 발생한 사건의 총 개수")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final BasicResponse basicResponse = new BasicResponse();

    @GetMapping("")
    @Operation(summary = "지역 별 발생 사건 총 개수", description = "지역 별 사건/사고 + 재난문자 + 지하철 지연의 총 개수")
    public ResponseEntity<BasicResponse> countTotalEvents() {
        return basicResponse.ok(
                locationService.countTotalEvents()
        );
    }
}
