package com.xiong.user.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xiong
 * @since 2020-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user")
@ApiModel(value="User对象", description="用户表")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "个性签名")
    private String remark;

    @ApiModelProperty(value = "性别，默认 1 为男，0 为女")
    private Boolean gender;

    @ApiModelProperty(value = "账号是否可用，默认 1 为可用，0 不可用")
    @Getter(value = AccessLevel.NONE)
    private Boolean enabled;

    @ApiModelProperty(value = "账号是否过期，默认 1 为不过期，0 为过期")
    private Boolean notExpired;

    @ApiModelProperty(value = "账号是否锁定，默认 1 为不锁定，0 为锁定")
    private Boolean accountNotLocked;

    @ApiModelProperty(value = "证书（密码）是否过期，默认 1 为不过期，0 为过期")
    private Boolean credentialsNotExpired;

    @ApiModelProperty(value = "上一次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore
    private Date gmtCreated;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    private Date gmtModified;

    @JsonIgnore
    @TableField(exist = false)
    private List<Role> roles;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //获取当前用户对象所具有的角色信息
        ArrayList<GrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return notExpired;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return accountNotLocked;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return credentialsNotExpired;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }
}
