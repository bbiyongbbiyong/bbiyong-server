package com.capstone.bbiyong.metro.controller;

import com.capstone.bbiyong.common.dto.BasicResponse;
import com.capstone.bbiyong.metro.service.MetroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@EnableScheduling
@RestController
@RequestMapping("metro")
@RequiredArgsConstructor
@Tag(name = "๐ Metro", description = "์งํ์ฒ  ์ง์ฐ ์ ๋ณด API")
public class MetroController {

    private final MetroService metroService;
    private final BasicResponse basicResponse = new BasicResponse();

    @GetMapping("/view")
    @Operation(summary = "์งํ์ฒ  ์ง์ฐ ์ ๋ณด ์กฐํ", description = "์งํ์ฒ  ์ง์ฐ ์ ๋ณด๋ฅผ ์กฐํํฉ๋๋ค")
    public ResponseEntity<BasicResponse> getMetro() {
        return basicResponse.ok(
                metroService.getMetro(LocalDateTime.now())
        );
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @DeleteMapping("/delete")
    @Operation(summary = "์ผ์ฃผ์ผ ์ง๋ ์งํ์ฒ  ์ง์ฐ ์ ๋ณด ์ญ์ ", description = "์ข๋ฃ๋์ง ์ผ์ฃผ์ผ์ด ์ง๋ ์งํ์ฒ  ์ง์ฐ ์ ๋ณด๋ฅผ DB์์ ์ญ์ ํฉ๋๋ค")
    public ResponseEntity<BasicResponse> deleteMetro () {
        metroService.deleteMetros();
        return basicResponse.noContent();
    }
}