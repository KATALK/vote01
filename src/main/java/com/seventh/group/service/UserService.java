package com.seventh.group.service;

import com.seventh.group.Entity.User;

import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/10--19:53
 * @Version 1.0
 */
public interface UserService {
    public User register(User user);
    public List<User> findAllUser();

    public User loginCheck(String username,String password);
    public boolean checkRegisterByUsername(String username);

    User selectByUsername(String username);

    List<Integer> selectArticleIdsByUsername(String username);
}
