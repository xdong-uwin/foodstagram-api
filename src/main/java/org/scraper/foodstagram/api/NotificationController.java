package org.scraper.foodstagram.api;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.dto.NotificationDto;
import org.scraper.foodstagram.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getNotifications(@RequestParam Long memberId) {
        return ResponseEntity.ok(notificationService.getNotifications(memberId));
    }

    @GetMapping("/{notificationId}/read")
    public ResponseEntity<Boolean> markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok(true);
    }
}
