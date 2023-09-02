package com.capstone.notification.controller;

import com.capstone.common.dto.BasicResponse;
import com.capstone.notification.dto.SubscribeRequestDTO;
import com.capstone.notification.service.TopicService;
import com.capstone.security.annotation.ReqMember;
import com.capstone.security.provider.SecurityUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topic")
@RequiredArgsConstructor
@Tag(name = "📩 Topic", description = "알림 구독 설정 API")
public class TopicController {


    private final TopicService topicService;
    private final BasicResponse basicResponse = new BasicResponse();

    @PostMapping
    @Operation(summary = "알람 토픽 설정", description = "알람 받을 토픽을 설정합니다.")
    public ResponseEntity<BasicResponse> saveSubscribeTopic(
            @ReqMember SecurityUserDetails securityUserDetails,
            @RequestBody SubscribeRequestDTO requestDTO) {
        topicService.saveTopic(securityUserDetails.member(), requestDTO);
        return basicResponse.noContent();
    }

    @GetMapping
    @Operation(summary = "알람 토픽 조회", description = "알람 받을 토픽을 조회합니다.")
    public ResponseEntity<BasicResponse> getSubscribeTopic(@ReqMember SecurityUserDetails securityUserDetails) {
        return basicResponse.ok(
                topicService.getTopic(securityUserDetails.member())
        );
    }
}