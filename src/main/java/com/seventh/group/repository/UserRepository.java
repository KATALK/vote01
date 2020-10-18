package com.seventh.group.repository;

import com.seventh.group.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/10--18:33
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
    User findByUsernameAndPassword(String username,String password);

    @Query(value = "SELECT a.`id` AS articleId FROM t_user u LEFT OUTER JOIN user_article ua ON ua.`user_id` = u.`id` \n" +
            "LEFT OUTER JOIN t_article a ON a.`id` = ua.`article_id`   WHERE u.username = ?1"
            ,nativeQuery = true)
    List<Integer> selectArticleIdsByUsername(String username);
}
