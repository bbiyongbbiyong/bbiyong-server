package com.capstone.bbiyong.openapi.controller;

import com.capstone.bbiyong.common.dto.BasicResponse;
import com.capstone.bbiyong.openapi.EmerMsgOpenAPI;
import com.capstone.bbiyong.openapi.MetroOpenAPI;
import com.capstone.bbiyong.openapi.OpenAPI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

import static org.springframework.http.HttpStatus.OK;

@EnableScheduling
@RestController
@RequestMapping("parse")
@RequiredArgsConstructor
@Tag(name = "ð OPEN API íì±", description = "ì¬ê±´/ì¬ê³ , ì¬ëë¬¸ì, ì§íì²  ì§ì° OPEN API")
public class OpenApiController {
    private final OpenAPI accidentOpenAPI;
    private final EmerMsgOpenAPI emerMsgOpenAPI;
    private final MetroOpenAPI metroOpenAPI;

    private final BasicResponse basicResponse = new BasicResponse();


    @Scheduled(fixedDelay = 60000)  // 1ë¶ ê°ê²©
    @GetMapping("/accident")
    @ResponseStatus(OK)
    @Operation(summary = "ì¬ê±´/ì¬ê³  ë° ìì API ìë í¸ì¶", description = "1ë¶ë§ë¤ ì¬ê±´/ì¬ê³  ë° ìì ì ë³´ë¥¼ ìë°ì´í¸íì¬ DBì ì ì¥í©ëë¤")
    public ResponseEntity<BasicResponse> callAccidentOpenAPI() throws IOException, ParseException, org.json.simple.parser.ParseException {
        accidentOpenAPI.call();
        return basicResponse.noContent();
    }

    @Scheduled(fixedDelay = 60000)  // 1ë¶ ê°ê²©
    @GetMapping("/emerMsg")
    @ResponseStatus(OK)
    @Operation(summary = "ì¬ëë¬¸ì API ìë í¸ì¶", description = "1ë¶ë§ë¤ ì¬ëë¬¸ì ì ë³´ë¥¼ ìë°ì´í¸íì¬ DBì ì ì¥í©ëë¤")
    public ResponseEntity<BasicResponse> callEmerMsgOpenAPI() throws IOException, ParseException {
        emerMsgOpenAPI.call();
        return basicResponse.noContent();
    }

    @Scheduled(fixedDelay = 60000)  // 1ë¶ ê°ê²©
    @GetMapping("/metro")
    @ResponseStatus(OK)
    @Operation(summary = "ì§íì²  ì§ì° API ìë í¸ì¶", description = "1ë¶ë§ë¤ ì§íì²  ì§ì° ê´ë ¨ ì ë³´ë¥¼ ìë°ì´í¸íì¬ DBì ì ì¥í©ëë¤")
    public ResponseEntity<BasicResponse> callTwitterOpenAPI() {
        metroOpenAPI.callOpenAPI();
        return basicResponse.noContent();
    }

}
