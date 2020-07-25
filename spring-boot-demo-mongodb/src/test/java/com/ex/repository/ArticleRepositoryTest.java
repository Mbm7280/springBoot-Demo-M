package com.ex.repository;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.ex.AppRunTest;
import com.ex.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Slf4j
public class ArticleRepositoryTest extends AppRunTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Autowired
//    private Snowflake snowflake;


    /**
     * 新增
     */
    @Test
    public void testSave(){
        Article article = new Article(1L, RandomUtil.randomString(20),RandomUtil.randomString(150),
                DateUtil.date(),DateUtil.date(),0L,0L);
        articleRepository.save(article);
        log.info("[article] = {}", JSONUtil.toJsonStr(article));
    }

    /**
     * 根据主键删除
     */
    @Test
    public void deleteArticle(){
        articleRepository.deleteById(1L);
    }


    /**
     * 删除所有
     */
    @Test
    public void deleteAll(){
        articleRepository.deleteAll();
    }


    /**
     * 修改
     */
    @Test
    public void updateArticle(){
        articleRepository.findById(1L).ifPresent(article -> {
            article.setTitle(article.getTitle() + "更新后的标题");
            article.setUpdateTime(DateUtil.date());
            articleRepository.save(article);
            log.info("[article] = {}",JSONUtil.toJsonStr(article));
        });
    }


    /**
     * 查询所有
     */
    @Test
    public void queryAll(){
        List<Article> articleList = articleRepository.findAll();
        articleList.stream().forEach(A -> {
            System.out.println(A);
        });
    }

    /**
     * 根据 ID 查询
     */
    @Test
    public void queryById(){
        Long id = 1L;
        Optional<Article> article = articleRepository.findById(id);
        System.out.println(article);
    }

    /**
     * 分页查询
     */
    @Test
    public void queryPageList(){
        Sort sort = Sort.by("thumbUp","updateTime").descending();
        PageRequest pageRequest = PageRequest.of(0,5,sort);
        Page<Article> all = articleRepository.findAll(pageRequest);
        log.info("[总页数] = {}",all.getTotalPages());
        log.info("[总条数] = {}",all.getTotalElements());
    }


    /**
     * Template
     * 自定义条件
     * 使用 mongoTemplate
     */
    @Test
    public void findByCriteria1(){
        Criteria criteria = new Criteria();
        criteria.is(1L);
        Query query = Query.query(criteria);
        List<Article> articles = mongoTemplate.find(query, Article.class);
        log.info("[article] = {}",articles.get(0));
    }

    /**
     * Criteria 的用法
     */
    @Test
    public void findByCriteria2(){
        Criteria criteria = new Criteria();
        // and("document").is/in...(匹配条件的规则)
        criteria.and("title").in("542vxwf0np9m6tkvbu0x更新后的标题");
        Query query = Query.query(criteria);
        List<Article> articles = mongoTemplate.find(query, Article.class);
        log.info("[article] = {}",articles.get(0));
    }


    /**
     * 自定义
     * 模糊查询
     */
    @Test
    public void testFindByTitleLike(){
        List<Article> aa = articleRepository.findByTitleLike("aa");
        log.info("[articles] = {}",JSONUtil.toJsonStr(aa));
    }


}
