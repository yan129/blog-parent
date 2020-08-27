package com.xiong.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiong.article.entity.Tag;
import com.xiong.article.mapper.TagMapper;
import com.xiong.article.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author xiong
 * @since 2020-08-27
 */
@Service
@Transactional
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    public void addTag(Tag tag) {
        baseMapper.insert(tag);
    }

    @Override
    public int updateTagById(Tag tag) {
        return baseMapper.updateById(tag);
    }

    @Override
    public int deleteTagById(String id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public List<Tag> findAllTag() {
        return baseMapper.selectList(null);
    }
}
