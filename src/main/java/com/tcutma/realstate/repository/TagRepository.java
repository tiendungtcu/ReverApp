package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.Tag;
import com.tcutma.realstate.service.dto.TagDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the Tag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("select count(tag.id)  from Tag tag where tag.tagName =:name")
    int findTagsByName(@Param("name") String name);
}
