package org.scraper.foodstagram.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class CommentDto {

    private Long id;

    private Long recipeId;

    private Long authorId;

    private String content;

    private Instant createdAt;

    private Instant updatedAt;
}
