package com.xiong.article.service;

import com.xiong.article.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author xiong
 * @since 2020-08-22
 */
public interface ArticleService extends IService<Article> {

    void addArticle(Article article);

    List<Article> findAllArticle();
}
