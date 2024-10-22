package org.scraper.foodstagram.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.scraper.foodstagram.dto.Ingredient;
import org.scraper.foodstagram.dto.RecipeDto;
import org.scraper.foodstagram.dto.Step;
import org.scraper.foodstagram.repository.entity.Recipe;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    @Mapping(target = "ingredients", expression = "java(convertIngredientStringToList(recipe.getIngredients()))")
    @Mapping(target = "steps", expression = "java(convertStepStringToList(recipe.getSteps()))")
    RecipeDto toDto(Recipe recipe);

    @Mapping(target = "ingredients", expression = "java(convertIngredientListToString(recipeDto.getIngredients()))")
    @Mapping(target = "steps", expression = "java(convertStepListToString(recipeDto.getSteps()))")
    Recipe toEntity(RecipeDto recipeDto);

    default String convertIngredientListToString(List<Ingredient> ingredients) {
        try {
            var objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(ingredients);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException("Error converting list to JSON string", exception);
        }
    }

    default String convertStepListToString(List<Step> steps) {
        try {
            var objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(steps);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException("Error converting list to JSON string", exception);
        }
    }

    default List<Ingredient> convertIngredientStringToList(String ingredients) {
        try {
            var objectMapper = new ObjectMapper();
            return objectMapper.readValue(ingredients, objectMapper.getTypeFactory().constructCollectionType(List.class, Ingredient.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON string to list", e);
        }
    }

    default List<Step> convertStepStringToList(String steps) {
        try {
            var objectMapper = new ObjectMapper();
            return objectMapper.readValue(steps, objectMapper.getTypeFactory().constructCollectionType(List.class, Step.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON string to list", e);
        }
    }
}
