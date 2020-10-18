package com.seventh.group.Entity;

import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/10/13--22:21
 * @Version 1.0
 */
public class responseDate {
    private int num;
    private String title;
    private List<String> data ;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
