package org.scraper.foodstagram.service;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.dto.NotificationDto;
import org.scraper.foodstagram.mapper.NotificationMapper;
import org.scraper.foodstagram.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    public List<NotificationDto> getNotifications(Long memberId) {
        return notificationRepository.findByReceiverId(memberId).stream()
                .map(notificationMapper::toDto)
                .toList();
    }
}
