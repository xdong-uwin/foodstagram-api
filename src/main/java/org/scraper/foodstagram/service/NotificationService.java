package org.scraper.foodstagram.service;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.dto.NotificationDto;
import org.scraper.foodstagram.dto.RecipeDto;
import org.scraper.foodstagram.mapper.NotificationMapper;
import org.scraper.foodstagram.repository.MemberRepository;
import org.scraper.foodstagram.repository.NotificationRepository;
import org.scraper.foodstagram.repository.entity.Notification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    private final MemberRepository memberRepository;

    public List<NotificationDto> getNotifications(Long memberId) {
        return notificationRepository.findByReceiverId(memberId).stream()
                .map(notificationMapper::toDto)
                .toList();
    }

    public void createLikeNotification(Long receiverId, RecipeDto recipe, Long likedById) {
        var likedBy = memberRepository.findById(likedById)
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + likedById));

        var notification = Notification.builder()
                .title("Like Received")
                .message(String.format("%s liked your recipe %s!",
                        likedBy.getFirstName() + " " + likedBy.getLastName(),
                        recipe.getTitle()))
                .timestamp(Instant.now())
                .isRead(false)
                .receiverId(receiverId)
                .build();
        notificationRepository.save(notification);
    }
}
