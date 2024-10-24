package org.scraper.foodstagram.mapper;

import org.mapstruct.Mapper;
import org.scraper.foodstagram.dto.RecipeTagDto;
import org.scraper.foodstagram.repository.entity.RecipeTag;

@Mapper(componentModel = "spring")
public interface RecipeTagMapper {

    RecipeTagDto toDto(RecipeTag recipeTag);

    RecipeTag toEntity(RecipeTagDto recipeTagDto);
}
