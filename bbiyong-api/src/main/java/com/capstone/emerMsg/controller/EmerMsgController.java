package com.capstone.emerMsg.controller;

import com.capstone.common.dto.BasicResponse;
import com.capstone.emerMsg.service.EmerMsgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;


@EnableScheduling
@RestController
@RequestMapping("emerMsg")
@Tag(name = "✉️Emergency Message", description = "재난 문자 API")
@RequiredArgsConstructor
public class EmerMsgController {
    private final EmerMsgService emerMsgService;
    private final BasicResponse basicResponse = new BasicResponse();

    @GetMapping("/{locationId}")
    @Operation(summary = "지역 별 재난문자 조회", description = "지역 별 발송된 재난문자를 조회합니다")
    public ResponseEntity<BasicResponse> getEmerMsgs(@PathVariable("locationId") Long locationId) {
        return basicResponse.ok(
                emerMsgService.getEmerMsg(locationId)
        );
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @DeleteMapping("/delete")
    @Operation(summary = "일주일 지난 재난문자 삭제", description = "종료된지 일주일이 지난 재난문자를 DB에서 삭제합니다")
    public ResponseEntity<BasicResponse> deleteEmerMsgs () {
        emerMsgService.deleteEmerMsgs();
        return basicResponse.noContent();
    }
}