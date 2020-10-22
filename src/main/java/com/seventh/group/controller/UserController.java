package com.seventh.group.controller;


import com.seventh.group.Entity.Article;
import com.seventh.group.Entity.User;
import com.seventh.group.service.ArticleService;
import com.seventh.group.service.UserService;

import com.seventh.group.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/10--19:52
 * @Version 1.0
 */
@Controller
@RequestMapping("/user.do")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private ArticleService articleService;

    @GetMapping("/index")
    public String index(@RequestParam(value = "start",defaultValue = "0")Integer start,
                        @RequestParam(value = "limit",defaultValue = "5")Integer limit, Model model,HttpSession session) {
        User user = (User)session.getAttribute("user");
        start = start <0 ? 0:start;
        Sort sort02 = Sort.by(Sort.Direction.DESC,"count");
        Pageable pageable02 = PageRequest.of(0,5,sort02);
        model.addAttribute("articleListTwo",articleService.listArticle(pageable02));//根据投票数倒叙查询
        Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
        Pageable pageable = PageRequest.of(start,limit,sort);
        Page<Article> articlePage = articleService.listArticle(pageable);//根据创建时间倒叙查询
        if (null!=user){
            List<Integer> articleIds = userService.selectArticleIdsByUsername(user.getUsername());
            if (articleIds.get(0)!=null){
                int i=0;
                for ( i=0;i<articlePage.getContent().size();i++){
                    for (int k=0;k<articleIds.size();k++){
                        if(articlePage.getContent().get(i).getId() == articleIds.get(k)){
                            articlePage.getContent().get(i).setType(1);//把类型设置为1，告知前端该文章用户已经投票过
                        }
                    }
                }
            }
        }
        model.addAttribute("articleList", articlePage);
        return "index";
    }
    @GetMapping("/toLogin")
    public String login() {
        return "login";
    }

    @GetMapping("/toRegister")
    public String toAddUserPage() {
        return "signIn";
    }

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @param session
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/toAddUser")
    public String toAddUser(@RequestParam("username") String username, @RequestParam("password") String password,
                            HttpSession session, RedirectAttributes redirectAttributes) {
        if ("".equals(username) || "".equals(password) || username == null || password == null) {
            redirectAttributes.addFlashAttribute("msg", "用户名或密码为空重重新注册");
            return "redirect:/user.do/toRegister";
        }
        boolean flag = userService.checkRegisterByUsername(username);
        if (flag) {
            redirectAttributes.addFlashAttribute("msg", "用户名已经存在，请换个名字");
            return "redirect:/user.do/toRegister";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Util.encode(password, "utf-8"));
        userService.register(user);
        user.setPassword(null);//密码不能传到前端，防止泄密
        session.setAttribute("user", user);
        return "redirect:/user.do/index";//redirect:/admin
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        HttpSession session, RedirectAttributes redirectAttributes) {
        if ("".equals(username) || "".equals(password) || username == null || password == null) {
            redirectAttributes.addFlashAttribute("msg", "用户名或密码为空,请重新登录");
            return "redirect:/user.do/toLogin";
        }
        User user = userService.loginCheck(username, MD5Util.encode(password,"utf-8"));
        if (null!=user){
            user.setPassword(null);//密码不能上传代前端
            session.setAttribute("user",user);
            return "redirect:/user.do/index";
        }else {
            redirectAttributes.addFlashAttribute("msg","账户或密码错误，请重新输入.......");
            return "redirect:/user.do/toLogin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.setAttribute("user",null);
        return "redirect:/";
    }

}

