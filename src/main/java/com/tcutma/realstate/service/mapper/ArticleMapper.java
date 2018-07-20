package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.ArticleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Article and its DTO ArticleDTO.
 */
@Mapper(componentModel = "spring", uses = {SupportCategoryMapper.class, UserMapper.class})
public interface ArticleMapper extends EntityMapper<ArticleDTO, Article> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    ArticleDTO toDto(Article article);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "userId", target = "user")
    Article toEntity(ArticleDTO articleDTO);

    default Article fromId(Long id) {
        if (id == null) {
            return null;
        }
        Article article = new Article();
        article.setId(id);
        return article;
    }
}
