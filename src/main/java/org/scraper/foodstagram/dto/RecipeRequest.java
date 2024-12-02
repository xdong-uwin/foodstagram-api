package org.scraper.foodstagram.dto;

import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class RecipeRequest {

    private String title;

    private String description;

    private File image;

    private List<Ingredient> ingredients;

    private List<Step> steps;

    private List<String> tags;

    private Long authorId;

    private List<Long> likedBy;

    private List<CommentDto> comments;

}
