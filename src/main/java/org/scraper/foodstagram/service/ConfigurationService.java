package org.scraper.foodstagram.service;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.dto.RecipeTagDto;
import org.scraper.foodstagram.mapper.RecipeTagMapper;
import org.scraper.foodstagram.repository.RecipeTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ConfigurationService {

    private final RecipeTagMapper recipeTagMapper;

    private final RecipeTagRepository recipeTagRepository;

    public List<RecipeTagDto> getRecipeTags() {
        return StreamSupport.stream(recipeTagRepository.findAll().spliterator(), false)
                .map(recipeTagMapper::toDto)
                .toList();
    }

    public RecipeTagDto addRecipeTag(RecipeTagDto recipeTagDto) {
        return recipeTagMapper.toDto(
                recipeTagRepository.save(recipeTagMapper.toEntity(recipeTagDto))
        );
    }
}
