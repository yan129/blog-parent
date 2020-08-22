package com.xiong.article.controller;


import com.xiong.article.entity.Article;
import com.xiong.article.service.ArticleService;
import com.xiong.common.util.R;
import com.xiong.common.util.ResponseCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author xiong
 * @since 2020-08-22
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "添加文章")
    @PostMapping("/add")
    public R addArticle(@RequestBody @Valid Article article, BindingResult errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return R.error(message);
        }
        articleService.addArticle(article);
        return R.success("文章添加成功");
    }

    @ApiOperation(value = "查找所有文章")
    @GetMapping("/findAll")
    public R<List<Article>> findAllArticle(){
        List<Article> allArticle = articleService.findAllArticle();
        return R.success(allArticle);
    }

    @ApiOperation(value = "根据ID更新文章")
    @PostMapping("/updateArticleById")
    public R updateArticleById(@RequestBody @Valid Article article, BindingResult errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return R.error(message);
        }
        boolean result = articleService.updateArticleById(article);
        return result ? R.success("文章更新成功") : R.error("文章更新失败");
    }

    @ApiOperation(value = "根据ID删除文章")
    @DeleteMapping("/deleteArticle/{id}")
    public R deleteArticleById(@PathVariable(value = "id", required = true) String id){
        boolean result = articleService.deleteArticleById(id);
        return result ? R.success("文章删除成功") : R.error(ResponseCode.ERROR.getMsg());
    }
}
