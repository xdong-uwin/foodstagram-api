package org.scraper.foodstagram.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.scraper.foodstagram.dto.CommentDto;
import org.scraper.foodstagram.mapper.CommentMapper;
import org.scraper.foodstagram.repository.CommentRepository;
import org.scraper.foodstagram.repository.entity.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CommentServiceTest {

    private static CommentService commentService;
    private static CommentRepository commentRepository;
    private static CommentMapper commentMapper;

    @BeforeAll
    static void setUp() {
        commentRepository = mock(CommentRepository.class);
        commentMapper = mock(CommentMapper.class);
        commentService = new CommentService(commentRepository, commentMapper);
    }

    @Test
    void should_save_comment_successfully() {
        // given
        CommentDto commentDto = CommentDto.builder().recipeId(1L).content("This is a comment").build();
        Comment comment = Comment.builder().recipeId(1L).content("This is a comment").build();

        when(commentMapper.toEntity(commentDto)).thenReturn(comment);

        // when
        commentService.commentRecipe(commentDto);

        // then
        verify(commentRepository).save(comment);
    }

    @Test
    void should_get_comments_successfully() {
        // given
        Long recipeId = 1L;
        CommentDto commentDto = CommentDto.builder().recipeId(recipeId).content("This is a comment").build();
        Comment comment = Comment.builder().recipeId(recipeId).content("This is a comment").build();

        when(commentRepository.findByRecipeId(recipeId)).thenReturn(List.of(comment));
        when(commentMapper.toDto(comment)).thenReturn(commentDto);

        // when
        List<CommentDto> comments = commentService.getComments(recipeId);

        // then
        assertThat(comments).containsExactly(commentDto);
    }
}