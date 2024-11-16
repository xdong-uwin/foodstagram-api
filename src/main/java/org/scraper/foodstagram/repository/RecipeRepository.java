package org.scraper.foodstagram.repository;

import org.scraper.foodstagram.repository.entity.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RecipeRepository extends PagingAndSortingRepository<Recipe, Long>, CrudRepository<Recipe, Long> {

    @Query("SELECT recipe FROM Recipe recipe WHERE recipe.title LIKE %?1% OR recipe.description LIKE %?1% ORDER BY recipe.createdAt DESC")
    Set<Recipe> findByTitleOrDescriptionContainingOrderByCreatedAt(String keyword);

}
