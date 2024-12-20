package org.scraper.foodstagram.service;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.dto.RecipeDto;
import org.scraper.foodstagram.dto.RecipeRequest;
import org.scraper.foodstagram.mapper.RecipeMapper;
import org.scraper.foodstagram.repository.MemberRepository;
import org.scraper.foodstagram.repository.RecipeRepository;
import org.scraper.foodstagram.repository.entity.Recipe;
import org.scraper.foodstagram.repository.file.DropboxStorage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private final RecipeMapper recipeMapper;

    private final CommentService commentService;

    private final NotificationService notificationService;

    private final MemberRepository memberRepository;

    private final DropboxStorage dropboxStorage;

    public RecipeDto createRecipe(RecipeRequest recipeDto) {
        setDefaultValuesForNewRecipe(recipeDto);
        var recipeEntity = recipeMapper.toEntity(recipeDto);
        if (recipeDto.getImage() != null) {
            var imageUrl = dropboxStorage.upload(recipeDto.getImage());
            recipeEntity.setImageUrl(imageUrl);
        }
        var savedRecipe = recipeRepository.save(recipeEntity);
        return recipeMapper.toDto(savedRecipe);
    }

    private void setDefaultValuesForNewRecipe(RecipeRequest recipeDto) {
        recipeDto.setLikedBy(List.of());
        recipeDto.setComments(List.of());
    }

    // By page
    public List<RecipeDto> getRecipes() {
        return StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .map(this::getRecipeFullInfo)
                .map(this::fillAuthorInfo)
                .collect(Collectors.toList());
    }

    public RecipeDto getRecipe(Long id) {
        return recipeRepository.findById(id)
                .map(this::getRecipeFullInfo)
                .map(this::fillAuthorInfo)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + id));
    }

    private RecipeDto fillAuthorInfo(RecipeDto recipeDto) {
        var member = memberRepository.findById(recipeDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + recipeDto.getAuthorId()));
        var author = member.getFirstName() + " " + member.getLastName();
        recipeDto.setAuthor(author);
        return recipeDto;
    }

    public RecipeDto updateRecipe(Long id, RecipeDto recipeDto) {
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

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public void likeRecipe(Long recipeId, Long memberId) {
        var recipe = recipeMapper.toDto(recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + recipeId)));
        var likedBys = recipe.getLikedBy();
        likedBys.add(memberId);
        recipe.setLikedBy(likedBys);
        if (!recipe.getAuthorId().equals(memberId)) {
            notificationService.createLikeNotification(recipe.getAuthorId(), recipe, memberId);
        }
        recipeRepository.save(recipeMapper.toEntity(recipe));
    }

    public void unlikeRecipe(Long recipeId, Long memberId) {
        var recipe = recipeMapper.toDto(recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + recipeId)));
        var likedBys = recipe.getLikedBy();
        likedBys.remove(memberId);
        recipe.setLikedBy(likedBys);
        recipeRepository.save(recipeMapper.toEntity(recipe));
    }

    private RecipeDto getRecipeFullInfo(Recipe recipe) {
        var recipeDto = recipeMapper.toDto(recipe);
        recipeDto.setComments(commentService.getComments(recipe.getId()));
        return recipeDto;
    }

    public List<RecipeDto> searchRecipesByKeyword(String keyword) {
        return recipeRepository.findByTitleOrDescriptionContainingOrderByCreatedAt(keyword).stream()
                .map(this::getRecipeFullInfo)
                .map(this::fillAuthorInfo)
                .collect(Collectors.toList());
    }

    public List<RecipeDto> getFavouriteRecipes(String memberId) {
        return StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .filter(recipe -> recipe.getLikedBy().contains(memberId))
                .map(this::getRecipeFullInfo)
                .map(this::fillAuthorInfo)
                .collect(Collectors.toList());
    }
}
