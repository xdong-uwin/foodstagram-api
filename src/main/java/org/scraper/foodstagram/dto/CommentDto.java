package org.scraper.foodstagram.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class CommentDto {

    private String id;

    private String recipeId;

    private String authorId;

    private String content;

    private Instant createdAt;

    private Instant updatedAt;
}
