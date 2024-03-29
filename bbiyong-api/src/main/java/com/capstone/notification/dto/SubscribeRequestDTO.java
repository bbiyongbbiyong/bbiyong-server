package com.capstone.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeRequestDTO {

    private boolean notifyOn;
    private NotificationListDTO notificationList;

}
