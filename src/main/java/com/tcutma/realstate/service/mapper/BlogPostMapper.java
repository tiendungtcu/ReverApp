package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.BlogPostDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BlogPost and its DTO BlogPostDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, UserMapper.class})
public interface BlogPostMapper extends EntityMapper<BlogPostDTO, BlogPost> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.categoryName", target = "categoryCategoryName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    BlogPostDTO toDto(BlogPost blogPost);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "userId", target = "user")
    BlogPost toEntity(BlogPostDTO blogPostDTO);

    default BlogPost fromId(Long id) {
        if (id == null) {
            return null;
        }
        BlogPost blogPost = new BlogPost();
        blogPost.setId(id);
        return blogPost;
    }
}
