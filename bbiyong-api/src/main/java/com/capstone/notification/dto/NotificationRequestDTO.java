package com.capstone.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDTO {

    private List<String> tokens;
    private String title;
    private String body;
}
