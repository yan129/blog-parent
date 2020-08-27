package com.xiong.article.mapper;

import com.xiong.article.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author xiong
 * @since 2020-08-22
 */
public interface ArticleMapper extends BaseMapper<Article> {

//    void addArticle(Article article);
//
//    List<Article> findAllArticle();
//
//    boolean updateArticleById(Article article);
//
//    boolean deleteArticleById(@Param("id") String id);
}
