package org.scraper.foodstagram.service;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.dto.RecipeDto;
import org.scraper.foodstagram.mapper.RecipeMapper;
import org.scraper.foodstagram.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private final RecipeMapper recipeMapper;

    public RecipeDto createRecipe(RecipeDto recipeDto) {
        var savedRecipe = recipeRepository.save(recipeMapper.toEntity(recipeDto));
        return recipeMapper.toDto(savedRecipe);
    }

    // By page
    public List<RecipeDto> getRecipes() {
        return StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .map(recipeMapper::toDto)
                .collect(Collectors.toList());
    }

    public RecipeDto getRecipe(String id) {
        return recipeRepository.findById(id)
                .map(recipeMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + id));
    }

    public RecipeDto updateRecipe(String id, RecipeDto recipeDto) {
        var recipe = recipeMapper.toDto(recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + id)));
        recipe.setTitle(recipeDto.getTitle());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setImageUrl(recipe.getImageUrl());
        recipe.setIngredients(recipeDto.getIngredients());
        recipe.setSteps(recipeDto.getSteps());
        var updatedRecipe = recipeRepository.save(recipeMapper.toEntity(recipe));
        return recipeMapper.toDto(updatedRecipe);
    }

    public void deleteRecipe(String id) {
        recipeRepository.deleteById(id);
    }

    public void likeRecipe(String recipeId, String memberId) {
        var recipe = recipeMapper.toDto(recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + recipeId)));
        var likedBys = recipe.getLikedBy();
        likedBys.add(memberId);
        recipe.setLikedBy(likedBys);
        recipeRepository.save(recipeMapper.toEntity(recipe));
    }

    public void unlikeRecipe(String recipeId, String memberId) {
        var recipe = recipeMapper.toDto(recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + recipeId)));
        var likedBys = recipe.getLikedBy();
        likedBys.remove(memberId);
        recipe.setLikedBy(likedBys);
        recipeRepository.save(recipeMapper.toEntity(recipe));
    }
}
