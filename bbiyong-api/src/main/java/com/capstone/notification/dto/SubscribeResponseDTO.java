package com.capstone.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class SubscribeResponseDTO {

    private boolean notifyOn;
    private NotificationListDTO notificationList;

    public static SubscribeResponseDTO from (SubscribeRequestDTO requestDTO) {
        return SubscribeResponseDTO.builder()
                .notifyOn(requestDTO.isNotifyOn())
                .notificationList(requestDTO.getNotificationList())
                .build();
    }
}
