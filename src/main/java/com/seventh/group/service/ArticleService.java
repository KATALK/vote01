package com.seventh.group.service;

import com.seventh.group.Entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/19--15:16
 * @Version 1.0
 */
public interface ArticleService {

    public void deleteArticle(int id);//根据主键id删除文章
    public int updateArticle(int id);//根据主键id更改文章
    public List<Article> selectAllArticle();//查询所有投票
    Page<Article> articlePage(Pageable pageable);
    void addArticleAndOption(String title, List<String> contents);

    Page<Article> listArticle(Pageable pageable);

    Article selectArticleById(int id);//根据id查询文章

    void save(Article article);

    Page<Article> saveAll(Page<Article> articlePage);

    Page<Article> searchArticle(String s, Pageable pageable);
}
