package com.seventh.group.repository;

import com.seventh.group.Entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/10--18:35
 * @Version 1.0
 */
public interface ArticleRepository extends JpaRepository<Article,Integer> {
    
    @Query("select b from  Article b order by b.createTime desc ")
    List<Article> findAll();


    @Query(value = "select a from Article a where a.title like ?1 ")
    Page<Article> SearchArticle(String query, Pageable pageable);
}
