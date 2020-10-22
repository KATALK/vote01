package com.seventh.group.controller;

import com.seventh.group.Entity.Article;
import com.seventh.group.Entity.Option;
import com.seventh.group.Entity.User;
import com.seventh.group.service.ArticleService;
import com.seventh.group.service.UserService;
import com.seventh.group.service.optionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/20--15:40
 * @Version 1.0
 */
@Controller
@RequestMapping("/article.do")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Resource
    private optionService optionService;

    @Autowired
    private UserService userService;



    @PostMapping("/search")
    public String search(@PageableDefault(size = 3,sort = {"createTime"},
    direction = Sort.Direction.DESC)Pageable pageable,@RequestParam String query,Model model,
                         HttpSession httpSession){
        User user = (User)httpSession.getAttribute("user");
        model.addAttribute("articleList",articleService.searchArticle("%"+query+"%",pageable));
        model.addAttribute("query",query);
        model.addAttribute("user",user);
        return "search";
    }

    /**
     * 跳转到添加文章页面
     * @return
     */
    @GetMapping("/add")
    public String add(){
        return "addArticle";
    }

    /**
     * 添加投票标题和选项
     * @param title
     * @param contents
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/addArticleAndOption")
    public String add(@RequestParam("title") String title, @RequestParam("content")List<String> contents,
                      HttpSession session,RedirectAttributes attributes){

            for (int i=0;i<contents.size();i++){
                if (contents.get(i)==null || contents.get(i).length()<1){
                    attributes.addFlashAttribute("msg","请把全部填空写完......");
                    attributes.addFlashAttribute("title",title);
                    return "redirect:/article.do/add"; }
            }
            articleService.addArticleAndOption(title,contents);
            return "redirect:/user.do/index";
    }


    //根据id查询
    @GetMapping("{id}")
    public String selectArticleId(@PathVariable("id")int id, Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        Article article = articleService.selectArticleById(id);
        model.addAttribute("article",article);
        return "votePage";
    }
    /**
     * 根据id删除
     * @param articleId
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteArticle/{id}")
    public String delete(@PathVariable("id") int articleId){
        articleService.deleteArticle(articleId);
        return "success";
    }


    @PostMapping("/getVoteData")
    public String getVote(@RequestParam("contentId")Integer contentId,@RequestParam("articleId")int articleId,HttpSession session,
            RedirectAttributes  attributes){
        Article article = articleService.selectArticleById(articleId);
        article.setCount(article.getCount()+1);//投票文章+1
        User u = (User)session.getAttribute("user");
        optionService.updateOptionNum(contentId);//更新选项被选中的次数
        User user = userService.selectByUsername(u.getUsername());
        article.getUsers().add(user);
        articleService.save(article);
        user.setPassword(null);
        attributes.addFlashAttribute("user",user);
        return "redirect:/user.do/index";
    }

}
