package org.scraper.foodstagram.api;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.dto.RecipeTagDto;
import org.scraper.foodstagram.service.ConfigurationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/configurations")
@RequiredArgsConstructor
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @GetMapping("/recipe-tags")
    public List<RecipeTagDto> getRecipeTags() {
        return configurationService.getRecipeTags();
    }

    @PostMapping("/recipe-tags")
    public RecipeTagDto addRecipeTag(RecipeTagDto recipeTagDto) {
        return configurationService.addRecipeTag(recipeTagDto);
    }
}
