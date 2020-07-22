package com.ex.repository;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.ex.AppRunTest;
import com.ex.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@Slf4j
public class ArticleRepositoryTest extends AppRunTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Snowflake snowflake;


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
     * 模糊查询
     */
    @Test
    public void testFindByTitleLike(){
        List<Article> aa = articleRepository.findByTitleLike("aa");
        log.info("[articles] = {}",JSONUtil.toJsonStr(aa));
    }


}
