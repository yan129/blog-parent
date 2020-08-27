package com.xiong.article.controller;


import com.xiong.article.entity.Theme;
import com.xiong.article.service.ThemeService;
import com.xiong.common.util.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author xiong
 * @since 2020-08-27
 */
@RestController
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @ApiOperation(value = "添加专题分类")
    @PostMapping("/add")
    public R addTheme(@RequestBody @Valid Theme theme, BindingResult errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return R.error(message);
        }
        themeService.addTheme(theme);
        return R.success("专题添加成功");
    }

    @ApiOperation(value = "更新专题")
    @PostMapping("/updateThemeById")
    public R updateThemeById(@RequestBody @Valid Theme theme, BindingResult errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return R.error(message);
        }
        int updateThemeById = themeService.updateThemeById(theme);
        return updateThemeById > 0 ? R.success("专题更新成功") : R.error("专题更新失败");
    }

    @ApiOperation(value = "查询所有专题")
    @GetMapping("/findAll")
    public R<List<Theme>> findAllTheme(){
        List<Theme> allTheme = themeService.findAllTheme();
        return R.success(allTheme);
    }

    @ApiOperation(value = "删除专题")
    @DeleteMapping("/delete")
    public R deleteThemeById(@ApiParam(value = "专题ID", required = true)
                                 @RequestParam(value = "id", required = true) String id){
        int deleteThemeById = themeService.deleteThemeById(id);
        return deleteThemeById > 0 ? R.success("专题删除成功") : R.error("专题删除失败");
    }
}
