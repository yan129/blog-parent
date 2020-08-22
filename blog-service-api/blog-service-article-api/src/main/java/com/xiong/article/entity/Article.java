package com.xiong.article.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author xiong
 * @since 2020-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_article")
@ApiModel(value="Article对象", description="文章表")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "分类ID")
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

    @ApiModelProperty(value = "浏览数")
    private Long views;

    @ApiModelProperty(value = "点赞数")
    private Long favour;

    @ApiModelProperty(value = "踩点击数")
    private Long disfavour;

    @ApiModelProperty(value = "是否开启打赏，默认0不开启，1开启")
    private Boolean reward;

    @ApiModelProperty(value = "是否开启评论功能，默认1开启，0不开启")
    private Boolean comment;

    @ApiModelProperty(value = "1默认发布，0存入草稿箱")
    private Boolean publish;

    @ApiModelProperty(value = "默认0不置顶，1置顶")
    private Boolean isTop;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreated;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
