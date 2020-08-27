package com.xiong.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiong.article.entity.ArticleTag;
import com.xiong.article.mapper.ArticleTagMapper;
import com.xiong.article.service.ArticleTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 文章标签关联表 服务实现类
 * </p>
 *
 * @author xiong
 * @since 2020-08-27
 */
@Service
@Transactional
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
    @Override
    public void addAT(ArticleTag articleTag) {

    }

    @Override
    public int updateAT(ArticleTag articleTag) {
        return 0;
    }

    @Override
    public int deleteAT(String id) {
        return 0;
    }

    @Override
    public ArticleTag findOneAT(String id) {
        return null;
    }

    @Override
    public void insertATBytBatch(List<ArticleTag> articleTags) {
        baseMapper.insertATBytBatch(articleTags);
    }
}
