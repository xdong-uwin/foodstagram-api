package org.scraper.foodstagram.repository;

import org.scraper.foodstagram.repository.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>, CrudRepository<Comment, Long> {

    List<Comment> findByRecipeId(Long recipeId);
}
