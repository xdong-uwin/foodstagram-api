package org.scraper.foodstagram.dto;

import lombok.Data;

@Data
public class NotificationDto {

    private Long id;

    private String title;

    private String message;

    private String timestamp;

    private Boolean isRead;
}
