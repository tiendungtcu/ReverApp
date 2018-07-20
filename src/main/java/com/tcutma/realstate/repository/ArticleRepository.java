package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.Article;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Article entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    @Query("select article from Article article where article.user.login = ?#{principal.username}")
    List<Article> findByUserIsCurrentUser();

}
