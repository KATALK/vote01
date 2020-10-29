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
@Data
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

}
