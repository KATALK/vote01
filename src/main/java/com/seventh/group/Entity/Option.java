package com.seventh.group.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author EdiMen
 * @Data 2020/9/10--15:04
 * @Version 1.0
 */
@Entity
@Table(name = "t_option")
public class Option implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//主键id

    private String content;//投票内容

    private int num;//记录用户投票数
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},optional = false)
    //可选属性，optional=false,表示article不能为空。删除option不影响article
    @JoinColumn(name = "article_id")
    private Article article;

    public Option(String content, Article article,int num) {
        this.content = content;
        this.article = article;
        this.num = num;
    }

    public Option() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
