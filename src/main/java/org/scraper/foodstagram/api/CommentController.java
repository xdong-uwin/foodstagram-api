package org.scraper.foodstagram.api;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.dto.CommentDto;
import org.scraper.foodstagram.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public void commentRecipe(@RequestBody CommentDto commentDto) {
        commentService.commentRecipe(commentDto);
    }

    @GetMapping("/{recipeId}/comments")
    public List<CommentDto> getComments(@PathVariable String recipeId) {
        return commentService.getComments(recipeId);
    }
}
