package org.scraper.foodstagram.repository;

import org.scraper.foodstagram.repository.entity.RecipeTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeTagRepository extends PagingAndSortingRepository<RecipeTag, Long>, CrudRepository<RecipeTag, Long> {

}
