package com.xiong.article.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xiong.article.entity.Theme;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author xiong
 * @since 2020-08-27
 */
public interface ThemeService extends IService<Theme> {

    void addTheme(Theme theme);

    int updateThemeById(Theme theme);

    int deleteThemeById(String id);

    List<Theme> findAllTheme();

}
