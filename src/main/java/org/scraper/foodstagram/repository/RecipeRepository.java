package org.scraper.foodstagram.repository;

import org.scraper.foodstagram.repository.entity.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends PagingAndSortingRepository<Recipe, String>, CrudRepository<Recipe, String> {


}
