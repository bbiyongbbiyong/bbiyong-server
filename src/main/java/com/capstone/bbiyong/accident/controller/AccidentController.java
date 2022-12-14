package com.capstone.bbiyong.accident.controller;

import com.capstone.bbiyong.accident.service.AccidentService;
import com.capstone.bbiyong.common.dto.BasicResponse;
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
@Tag(name = "๐๏ธ Accident ", description = "์ฌ๊ฑด/์ฌ๊ณ  API")
public class AccidentController {

    private final AccidentService accidentService;
    private final BasicResponse basicResponse = new BasicResponse();


    @GetMapping("/{locationId}")
    @Operation(summary = "์ง์ญ ๋ณ ์ฌ๊ฑด/์ฌ๊ณ  ์กฐํ", description = "์ง์ญ ๋ณ ์ฌ๊ฑด/์ฌ๊ณ ๋ฅผ ์กฐํํฉ๋๋ค")
    public ResponseEntity<BasicResponse> getAccident(@PathVariable("locationId") Long locationId) {
        return basicResponse.ok(
                accidentService.getAccident(locationId)
        );
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @DeleteMapping("/delete")
    @Operation(summary = "์ผ์ฃผ์ผ ์ง๋ ์ฌ๊ฑด/์ฌ๊ณ  ์ญ์ ", description = "์ข๋ฃ๋์ง ์ผ์ฃผ์ผ์ด ์ง๋ ์ฌ๊ฑด/์ฌ๊ณ ๋ฅผ DB์์ ์ญ์ ํฉ๋๋ค")
    public ResponseEntity<BasicResponse> deleteAccidents () {
        accidentService.deleteAccidents();
        return basicResponse.noContent();
    }

    @GetMapping("/most")
    @Operation(summary = "์ต๋น๊ฐ ์กฐํ", description = "์ง๋ ์ผ์ฃผ์ผ ๊ฐ ์์ธ ์ ์ญ์์ ๊ฐ์ฅ ๋ง์ด ๋ฐ์ํ ์ฌ๊ณ  ์ ํ ๋ฐ ํด๋น ์ฌ๊ณ  ์ ํ ์ต๋ค ๋ฐ์ ๊ตฌ๋ฅผ ์กฐํํฉ๋๋ค.")
    public ResponseEntity<BasicResponse> getMostAccident() {
        return basicResponse.ok(
                accidentService.getMostAccident()
        );
    }
}
