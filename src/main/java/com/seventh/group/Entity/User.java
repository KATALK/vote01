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
    @ManyToMany(cascade = CascadeType.ALL,targetEntity = Article.class,fetch = FetchType.EAGER)
    @JoinTable(name = "user_article",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}
            ,inverseJoinColumns = {@JoinColumn(name = "article_id",referencedColumnName = "id")})
    private List<Article> articles = new ArrayList<>();

    public User() {
    }

    public User(String username, @NotNull(message = "密码不能为空") String password, int type, int status, long number, List<Article> articles) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.status = status;
        this.number = number;
        this.articles = articles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", number=" + number +
                ", articles=" + articles +
                '}';
    }
}
