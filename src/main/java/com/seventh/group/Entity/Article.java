package com.seventh.group.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author EdiMen
 * @Data 2020/9/10--13:33
 * @Version 1.0
 */


@Entity
@Table(name = "t_article")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //主键id

    private String title;//文章标题

    private int type; //文章类型，0代表可以投票，1代表不可以投票

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;//文章创建时间

    private int count;//记录投票数
    @ManyToMany(cascade = CascadeType.ALL,targetEntity = User.class)
    @JoinTable(name = "user_article",joinColumns = {@JoinColumn(name = "article_id",referencedColumnName = "id")}
    ,inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")})
    private List<User> users =new ArrayList<>();

    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Option> options ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Article() {
        super();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", type=" + type +
//                ", createTime=" + createTime +
//                ", users=" + users +
//                ", options=" + options +
//                '}';
//    }
}
