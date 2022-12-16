package com.capstone.bbiyong.emerMsg.controller;

import com.capstone.bbiyong.common.dto.BasicResponse;
import com.capstone.bbiyong.emerMsg.dto.EmerMsgResponseDTO;
import com.capstone.bbiyong.emerMsg.service.EmerMsgService;
import com.capstone.bbiyong.openapi.EmerMsgOpenAPI;
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
@RequestMapping("emerMsg")
@Tag(name = "✉️Emergency Message", description = "재난 문자 API")
@RequiredArgsConstructor
public class EmerMsgController {
    private final EmerMsgOpenAPI emerMsgOpenAPI;
    private final EmerMsgService emerMsgService;
    private final BasicResponse basicResponse = new BasicResponse();

    @Scheduled(fixedDelay = 60000)  // 1분 간격
    @GetMapping("")
    @ResponseStatus(OK)
    @Operation(summary = "재난문자 API 자동 호출", description = "1분마다 재난문자 정보를 업데이트하여 DB에 저장합니다")
    public void callEmerMsgOpenAPI() throws IOException, ParseException {
        emerMsgOpenAPI.call();
    }

    @GetMapping("/{locationId}")
    @Operation(summary = "지역 별 재난문자 조회", description = "지역 별 발송된 재난문자를 조회합니다")
    public List<EmerMsgResponseDTO> getEmerMsgs(@PathVariable("locationId") Long locationId) {
        return emerMsgService.getEmerMsg(locationId);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @DeleteMapping("/delete")
    @Operation(summary = "일주일 지난 재난문자 삭제", description = "종료된지 일주일이 지난 재난문자를 DB에서 삭제합니다")
    public ResponseEntity<BasicResponse> deleteEmerMsgs () {
        emerMsgService.deleteEmerMsgs();
        return basicResponse.noContent();
    }
}