package org.scraper.foodstagram.service;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.dto.CommentDto;
import org.scraper.foodstagram.mapper.CommentMapper;
import org.scraper.foodstagram.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    public void commentRecipe(CommentDto commentDto) {
        commentRepository.save(commentMapper.toEntity(commentDto));
    }

    public List<CommentDto> getComments(Long recipeId) {
        return commentRepository.findByRecipeId(recipeId).stream()
                .map(commentMapper::toDto)
                .toList();
    }
}
