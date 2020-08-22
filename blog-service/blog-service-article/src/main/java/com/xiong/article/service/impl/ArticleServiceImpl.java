package com.xiong.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiong.article.entity.Article;
import com.xiong.article.mapper.ArticleMapper;
import com.xiong.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author xiong
 * @since 2020-08-22
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
        articleMapper.addArticle(article);
    }

    @Override
    public List<Article> findAllArticle() {
        return articleMapper.findAllArticle();
    }

    @Override
    public boolean updateArticleById(Article article) {
        return articleMapper.updateArticleById(article);
    }

    @Override
    public boolean deleteArticleById(String id) {
        return articleMapper.deleteArticleById(id);
    }
}
