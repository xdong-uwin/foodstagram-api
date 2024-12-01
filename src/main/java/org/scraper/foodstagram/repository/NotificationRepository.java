package org.scraper.foodstagram.repository;

import org.scraper.foodstagram.repository.entity.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long>, CrudRepository<Notification, Long> {

    List<Notification> findByReceiverId(Long memberId);
}
