package com.xiong.article.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xiong.article.entity.Tag;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author xiong
 * @since 2020-08-27
 */
public interface TagService extends IService<Tag> {

    void addTag(Tag tag);

    int updateTagById(Tag tag);

    int deleteTagById(String id);

    List<Tag> findAllTag();
}
