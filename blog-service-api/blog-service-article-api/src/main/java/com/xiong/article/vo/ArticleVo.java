package com.xiong.article.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVo implements Serializable {

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "分类ID")
    @NotEmpty(message = "专题不能为空")
    private String themeId;

    @ApiModelProperty(value = "标题")
    @NotEmpty(message = "标题不能为空")
    @Length(max = 50, message = "标题长度不能大于50字")
    private String title;

    @ApiModelProperty(value = "展示封面图")
    private String imgSrc;

    @ApiModelProperty(value = "内容")
    @NotEmpty(message = "文章内容不能为空")
    private String content;
}
