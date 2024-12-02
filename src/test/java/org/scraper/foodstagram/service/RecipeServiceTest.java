package org.scraper.foodstagram.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.scraper.foodstagram.dto.Ingredient;
import org.scraper.foodstagram.dto.RecipeRequest;
import org.scraper.foodstagram.dto.Step;
import org.scraper.foodstagram.mapper.RecipeMapper;
import org.scraper.foodstagram.repository.MemberRepository;
import org.scraper.foodstagram.repository.RecipeRepository;
import org.scraper.foodstagram.repository.entity.Recipe;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RecipeServiceTest {

    private static RecipeService recipeService;
    private static RecipeRepository recipeRepository;
    private static RecipeMapper recipeMapper;
    private static CommentService commentService;
    private static MemberRepository memberRepository;
    private static NotificationService notificationService;

    @BeforeAll
    static void setUp() {
        recipeRepository = mock(RecipeRepository.class);
        recipeMapper = mock(RecipeMapper.class);
        commentService = mock(CommentService.class);
        memberRepository = mock(MemberRepository.class);
        recipeService = new RecipeService(recipeRepository, recipeMapper, commentService, notificationService, memberRepository);
    }

    @Test
    void should_create_recipe_successfully() {
        // given
        RecipeRequest recipeRequest = new RecipeRequest();
        recipeRequest.setTitle("Recipe title");
        recipeRequest.setDescription("Recipe description");
        recipeRequest.setImageUrl("Recipe image URL");
        recipeRequest.setIngredients(List.of(new Ingredient("Ingredient 1", "5", "gram"), new Ingredient("Ingredient 2", "15", "gram")));
        recipeRequest.setSteps(List.of(new Step(1, "Step 1"), new Step(2, "Step 2")));

        Recipe recipe = new Recipe();
        recipe.setTitle("Recipe title");
        recipe.setDescription("Recipe description");
        recipe.setImageUrl("Recipe image URL");
        recipe.setIngredients("{\"ingredients\":[{\"name\":\"Ingredient 1\",\"amount\":\"5\",\"unit\":\"gram\"},{\"name\":\"Ingredient 2\",\"amount\":\"15\",\"unit\":\"gram\"}}");
        recipe.setSteps("{\"steps\":[{\"number\":1,\"description\":\"Step 1\"},{\"number\":2,\"description\":\"Step 2\"}]");

        when(recipeMapper.toEntity(recipeRequest)).thenReturn(recipe);

        // when
        recipeService.createRecipe(recipeRequest);

        // then
        verify(recipeRepository).save(recipe);
    }

}