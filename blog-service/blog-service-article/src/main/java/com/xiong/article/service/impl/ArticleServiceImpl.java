package com.xiong.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiong.article.entity.Article;
import com.xiong.article.entity.ArticleTag;
import com.xiong.article.mapper.ArticleMapper;
import com.xiong.article.service.ArticleService;
import com.xiong.article.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
@Transactional
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public void addArticle(Article article) {
        boolean saveOrUpdate = this.saveOrUpdate(article);
        // 批量插入文章标签
        if (saveOrUpdate){
            List<ArticleTag> list = new ArrayList<>();
            for (String tagId : article.getTagIds()) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(article.getId());
                articleTag.setTagId(tagId);
                list.add(articleTag);
            }
            articleTagService.insertATBytBatch(list);
        }
    }

    @Override
    public List<Article> findAllArticle() {
        return articleMapper.selectList(null);
    }

    @Override
    public int updateArticleById(Article article) {
        return articleMapper.updateById(article);
    }

    @Override
    public int deleteArticleById(String id) {
        return articleMapper.deleteById(id);
    }
}
