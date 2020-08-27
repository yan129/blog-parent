package com.xiong.article.controller;


import com.xiong.article.entity.Tag;
import com.xiong.article.service.TagService;
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
 * 标签表 前端控制器
 * </p>
 *
 * @author xiong
 * @since 2020-08-27
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation(value = "添加标签")
    @PostMapping("/add")
    public R addTag(@RequestBody @Valid Tag tag, BindingResult errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return R.error(message);
        }
        tagService.addTag(tag);
        return R.success("标签添加成功");
    }

    @ApiOperation(value = "更新标签")
    @PostMapping("/updateTagById")
    public R updateTagById(@RequestBody @Valid Tag tag, BindingResult errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return R.error(message);
        }
        int updateTagById = tagService.updateTagById(tag);
        return updateTagById > 0 ? R.success("标签更新成功") : R.error("标签更新失败");
    }

    @ApiOperation(value = "删除标签")
    @DeleteMapping("/delete")
    public R deleteTagById(@ApiParam(value = "标签ID",required = true)
                               @RequestParam(value = "id", required = true) String id){
        int deleteTagById = tagService.deleteTagById(id);
        return deleteTagById > 0 ? R.success("标签删除成功") : R.error("标签删除失败");
    }

    @ApiOperation(value = "查询所有标签")
    @GetMapping("/findAll")
    public R findAllTag(){
        List<Tag> allTag = tagService.findAllTag();
        return R.success(allTag);
    }

}
