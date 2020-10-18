package com.seventh.group.service.impl;

import com.seventh.group.Entity.User;
import com.seventh.group.repository.UserRepository;
import com.seventh.group.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/10--19:53
 * @Version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //根据姓名检查注册姓名是否重复
    @Override
    public boolean checkRegisterByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user!=null ){
            return true;
        }
        return false;
    }

    @Override
    public User selectByUsername(String username) {
        return  userRepository.findByUsername(username);
    }

    //根据用户名查询用户关联过的文章id集合
    @Override
    public List<Integer> selectArticleIdsByUsername(String username) {
        return userRepository.selectArticleIdsByUsername(username);
    }

    //查询所有
    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    //登录检查
    public User loginCheck(String username,String password){
       return userRepository.findByUsernameAndPassword(username, password);
    }

    //注册
    @Override
    public User register(User user) {
        return userRepository.saveAndFlush(user);
    }
}
