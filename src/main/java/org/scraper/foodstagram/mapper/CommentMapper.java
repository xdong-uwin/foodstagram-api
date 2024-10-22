package org.scraper.foodstagram.mapper;

import org.mapstruct.Mapper;
import org.scraper.foodstagram.dto.CommentDto;
import org.scraper.foodstagram.repository.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toDto(Comment comment);

    Comment toEntity(CommentDto commentDto);
}
