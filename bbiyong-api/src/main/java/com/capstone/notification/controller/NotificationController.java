package com.capstone.notification.controller;

import com.capstone.common.dto.BasicResponse;
import com.capstone.notification.dto.FcmTokenRequestDTO;
import com.capstone.notification.dto.NotificationRequestDTO;
import com.capstone.notification.service.FirebaseService;
import com.capstone.notification.service.NotificationService;
import com.capstone.security.annotation.ReqMember;
import com.capstone.security.provider.SecurityUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notification")
@RequiredArgsConstructor
@Tag(name = "🔔 Notification", description = "알림 설정 API")
public class NotificationController {

    private final NotificationService notificationService;
    private final FirebaseService firebaseService;
    private final BasicResponse basicResponse = new BasicResponse();

    @PostMapping("/fcmtoken")
    @Operation(summary = "fcm 토큰 저장", description = "fcm 토큰을 저장합니다.")
    public ResponseEntity<BasicResponse> saveFcmToken(
            @ReqMember SecurityUserDetails securityUserDetails,
            @RequestBody FcmTokenRequestDTO requestDTO) {
        notificationService.saveFcmToken(securityUserDetails.member(), requestDTO);
        return basicResponse.noContent();
    }

    @PostMapping("")
    @Operation(summary = "알람 전송 api", description = "알람을 전송합니다.")
    public ResponseEntity<BasicResponse> sendNotification(
            @RequestBody NotificationRequestDTO requestDTO) {
        firebaseService.sendNotification(requestDTO.getTokens(), requestDTO.getTitle(), requestDTO.getBody());
        return basicResponse.noContent();
    }
}
