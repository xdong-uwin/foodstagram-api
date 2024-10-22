package org.scraper.foodstagram.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Data
public class Recipe {

    @Id
    private String id;

    private String title;

    private String description;

    private String imageUrl;

    private String ingredients;

    private String steps;

    private String authorId;

    private String likedBy;

    private String comments;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
