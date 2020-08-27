package com.xiong.article.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiong.article.entity.ArticleTag;

import java.util.List;

/**
 * <p>
 * 文章标签关联表 Mapper 接口
 * </p>
 *
 * @author xiong
 * @since 2020-08-27
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    void insertATBytBatch(List<ArticleTag> articleTags);

}
