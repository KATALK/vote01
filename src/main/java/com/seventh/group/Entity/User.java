package com.seventh.group.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/10--13:28
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "t_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//主键

    private String username;//用户名
    @NotNull(message = "密码不能为空")
    private String password;//密码

    private int type;//

    private int status;//状态

    private long number;//投票数
//    @ManyToMany(cascade = CascadeType.ALL,targetEntity = Article.class,fetch = FetchType.EAGER)
//    @JoinTable(name = "user_article",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}
//            ,inverseJoinColumns = {@JoinColumn(name = "article_id",referencedColumnName = "id")})
    @ManyToMany(mappedBy = "users")
    private List<Article> articles = new ArrayList<>();


}
