package com.capstone.bbiyong.location.controller;

import com.capstone.bbiyong.common.dto.BasicResponse;
import com.capstone.bbiyong.location.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("location")
@Tag(name = "π΄ Location Information", description = "μ§μ­ λ³ λ°μν μ¬κ±΄μ μ΄ κ°μ")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final BasicResponse basicResponse = new BasicResponse();

    @GetMapping("")
    @Operation(summary = "μ§μ­ λ³ λ°μ μ¬κ±΄ μ΄ κ°μ", description = "μ§μ­ λ³ μ¬κ±΄/μ¬κ³  + μ¬λλ¬Έμ + μ§νμ²  μ§μ°μ μ΄ κ°μ")
    public ResponseEntity<BasicResponse> countTotalEvents() {
        return basicResponse.ok(
                locationService.countTotalEvents()
        );
    }
}
