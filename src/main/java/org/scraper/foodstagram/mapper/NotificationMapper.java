package org.scraper.foodstagram.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.scraper.foodstagram.dto.NotificationDto;
import org.scraper.foodstagram.repository.entity.Notification;

import java.time.Duration;
import java.time.Instant;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "timestamp", expression = "java(timeAgo(notification.getTimestamp()))")
    NotificationDto toDto(Notification notification);

    default String timeAgo(Instant timestamp) {
        Duration duration = Duration.between(timestamp, Instant.now());
        long seconds = duration.getSeconds();

        if (seconds < 60) {
            return seconds + " seconds ago";
        } else if (seconds < 3600) {
            long minutes = seconds / 60;
            return minutes + " minutes ago";
        } else if (seconds < 86400) {
            long hours = seconds / 3600;
            return hours + " hours ago";
        } else {
            long days = seconds / 86400;
            return days + " days ago";
        }
    }
}
