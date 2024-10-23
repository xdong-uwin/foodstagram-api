package org.scraper.foodstagram.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class RecipeDto {

    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    private List<Ingredient> ingredients;

    private List<Step> steps;

    private Long authorId;

    private List<Long> likedBy;

    private List<CommentDto> comments;

    private Instant createdAt;

    private Instant updatedAt;
}
