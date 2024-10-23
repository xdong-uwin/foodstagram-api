package org.scraper.foodstagram.api;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.dto.RecipeDto;
import org.scraper.foodstagram.service.RecipeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public RecipeDto createRecipe(@RequestBody RecipeDto recipeDto) {
        return recipeService.createRecipe(recipeDto);
    }

    @GetMapping
    public List<RecipeDto> getRecipes() {
        return recipeService.getRecipes();
    }

    @GetMapping("/{id}")
    public RecipeDto getRecipe(@PathVariable Long id) {
        return recipeService.getRecipe(id);
    }

    @PutMapping("/{id}")
    public RecipeDto updateRecipe(@PathVariable Long id, @RequestBody RecipeDto recipeDto) {
        return recipeService.updateRecipe(id, recipeDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }

    @PatchMapping("/{recipeId}/like")
    public void likeRecipe(@PathVariable Long recipeId, @RequestParam Long memberId) {
        recipeService.likeRecipe(recipeId, memberId);
    }

    @PatchMapping("/{recipeId}/unlike")
    public void unlikeRecipe(@PathVariable Long recipeId, @RequestParam Long memberId) {
        recipeService.unlikeRecipe(recipeId, memberId);
    }
}
