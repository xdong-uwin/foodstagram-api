package org.scraper.foodstagram.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class RecipeDto {

    private String id;

    private String title;

    private String description;

    private String imageUrl;

    private List<Ingredient> ingredients;

    private List<Step> steps;

    private String authorId;

    private List<String> likedBy;

    private Instant createdAt;

    private Instant updatedAt;
}
