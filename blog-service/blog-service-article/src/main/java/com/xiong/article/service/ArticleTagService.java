package com.xiong.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiong.article.entity.ArticleTag;

import java.util.List;

/**
 * <p>
 * 文章标签关联表 服务类
 * </p>
 *
 * @author xiong
 * @since 2020-08-27
 */
public interface ArticleTagService extends IService<ArticleTag> {

    void addAT(ArticleTag articleTag);

    int updateAT(ArticleTag articleTag);

    int deleteAT(String id);

    ArticleTag findOneAT(String id);

    void insertATBytBatch(List<ArticleTag> articleTags);

}
