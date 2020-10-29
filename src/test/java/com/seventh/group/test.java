package com.seventh.group;



import com.seventh.group.Entity.Article;
import com.seventh.group.Entity.Option;
import com.seventh.group.Entity.User;
import com.seventh.group.repository.ArticleRepository;
import com.seventh.group.repository.OptionRepository;
import com.seventh.group.repository.UserRepository;
import com.seventh.group.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author EdiMen
 * @Data 2020/9/19--15:47
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class test {

    @Autowired
    private ArticleRepository articleRepository;


    @Autowired
    private UserRepository userRepository;

    @Resource
    private OptionRepository optionRepository;

    @Test
    public void test(){
        Article article = new Article();
        article.setTitle("2020年娱乐圈谁的演技最厉害");
        article.setCreateTime(new Date());
        article.setType(0);
        List<String> list = Arrays.asList("12","34","56");
        List<Option> options = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            Option option = new Option();
            option.setContent(list.get(i));
            option.setArticle(article);
            options.add(option);
        }
        article.setOptions(options);
//        Option option = new Option();
//        option.setContent("我不喜欢你");
//        Option option01 = new Option();
//        option01.setContent("我不喜欢");
//        List<Option> options = new ArrayList<>();
//        options.add(option);
//        options.add(option01);
//        option01.setArticle(article);
//        option.setArticle(article);
//        article.setOptions(options);
       // articleRepository.save(article);
//        optionRepository.saveAll(Arrays.asList(option,option01));
//        option.setArticleId(article.getId());

       articleRepository.save(article);
    }

    @Test
    public void demo002(){
        Article article = articleRepository.findById(10).get();
        article.setTitle("周杰伦的《晴天》是你的青春吗？");
        article.setCreateTime(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);
//        article.setCreateTime(new Date());
//        article.setTitle("我喜欢你");
////        article.setType(0);
//        articleRepository.save(article);
//        Option option = new Option();
//        option.setContent("是的");
//      Option  option02 = new Option();
//      option02.setContent("不是");
//        option.setArticle(article);//article跟option相互设置关联
//        option02.setArticle(article);
//       article.setOptions(Arrays.asList(option,option02));
////        optionRepository.save(option);
//        articleRepository.saveAndFlush(article);
        articleRepository.saveAndFlush(article);
    }

    @Test
    public void demo003(){
        Article article = articleRepository.findById(10).get();
        Date createTime = article.getCreateTime();
        String string = createTime.toString();
        System.out.println(string);
        System.out.println(new Date().toString());
    }

    @Test
    public void demo04(){
        List<Article> all = articleRepository.findAll();
       for (int i=0;i<all.size();i++){
           System.out.println(all.get(i).getTitle());
           for (int k = 0;k<all.get(i).getOptions().size();k++){
               System.out.println(all.get(i).getOptions().get(k).getContent());
           }
           System.out.println("=================================             ");
       }
    }

//    @Transactional
//    @Rollback(false)
    @Test
    public void demo05(){
        User user = userRepository.findById(40).get();
//        Article article = articleRepository.findById(14).get();
        Article article1 = articleRepository.findById(15).get();
        article1.setCount(article1.getCount()+1);
//        article.setUsers(Arrays.asList(user));
        article1.setUsers(Arrays.asList(user));

//        user.getArticles().add(article);
//        user.getArticles().add(article1);
      articleRepository.save(article1);
    }

    @Transactional
    @Rollback(false)
    @Test
    public void demo06(){
        User user = userRepository.findById(40).get();
//        List<Article> articles = user.getArticles();
//        System.out.println(articles.size());
//        Article article = articleRepository.findById(12).get();
//        int size = article.getUsers().size();
//        System.out.println(size);
        List<Article> articles = user.getArticles();
        for (int i = 0;i<articles.size();i++){
            User user1 = articles.get(i).getUsers().get(0);
            System.out.println(user1.getUsername());
        }

    }

    @Autowired
    private ArticleService articleService;

    @Test
    public void demo07(){
        List<Integer> integers = userRepository.selectArticleIdsByUsername("admin");

        for (int i = 0;i<integers.size();i++){
            Integer integer = integers.get(i);
           if (integer==null){
               System.out.println("========");
           }else {
               System.out.println("+++++++++++");
           }
        }
//        Pageable pageable = PageRequest.of(0,10, Sort.by("count"));
//        Page<Article> articlePage = articleService.listArticle(pageable);
//        List<Article> articles = articlePage.toList();
//        Page<Article> articlePage1 = DataUtils.listToPage(articles, pageable);
//        Article article = articlePage1.getContent().get(7);
//        System.out.println(article.getCount());

    }



    @Test
    public void demo08(){
//        Optional<Article> byId = articleRepository.findById(9);
//        List<Option> options = byId.get().getOptions();
//        int a = 0;
//        articleRepository.deleteById(13);
//        articleRepository.deleteById(14);
//        articleRepository.deleteById(15);
//        articleRepository.deleteById(16);
//        userRepository.deleteById(37);

    }
}
