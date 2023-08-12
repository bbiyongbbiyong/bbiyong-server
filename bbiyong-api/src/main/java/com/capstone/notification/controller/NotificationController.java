package com.capstone.notification.controller;

import com.capstone.common.dto.BasicResponse;
import com.capstone.member.domain.Member;
import com.capstone.notification.dto.SubscribeRequestDTO;
import com.capstone.notification.service.NotificationService;
import com.capstone.security.annotation.ReqMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topic")
@RequiredArgsConstructor
@Tag(name = "🔔 Notification", description = "알림 구독 설정 API")
public class NotificationController {


    private final NotificationService notificationService;
    private final BasicResponse basicResponse = new BasicResponse();


    @PostMapping("/save")
    @Operation(summary = "알람 토픽 설정", description = "알람 받을 토픽을 설정합니다.")
    public ResponseEntity<BasicResponse> saveSubscribeTopic(
            @ReqMember Member member,
            @RequestBody SubscribeRequestDTO requestDTO) {
        notificationService.saveTopic(member, requestDTO);
        return basicResponse.noContent();
    }

    @GetMapping("/get")
    @Operation(summary = "알람 토픽 조회", description = "알람 받을 토픽을 조회합니다.")
    public ResponseEntity<BasicResponse> getSubscribeTopic(@ReqMember Member member) {
        return basicResponse.ok(
                notificationService.getTopic(member.getId())
        );
    }
}