package com.xiong.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiong.article.entity.Theme;
import com.xiong.article.mapper.ThemeMapper;
import com.xiong.article.service.ThemeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author xiong
 * @since 2020-08-27
 */
@Service
@Transactional
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements ThemeService {
    @Override
    public void addTheme(Theme theme) {
        baseMapper.insert(theme);
    }

    @Override
    public int updateThemeById(Theme theme) {
        return baseMapper.updateById(theme);
    }

    @Override
    public int deleteThemeById(String id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public List<Theme> findAllTheme() {
        return baseMapper.selectList(null);
    }
}
