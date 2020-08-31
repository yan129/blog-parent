package com.xiong.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户名")
    @Length(min = 11, max = 11, message = "请输入正确手机号！")
    @Pattern(regexp = "^[1][34578]\\d{9}$", message = "请输入正确手机号！")
    private String username;

    @ApiModelProperty(value = "密码")
    @Length(min = 6, max = 20, message = "密码长度介于6~20之间")
    private String password;

    @ApiModelProperty(value = "头像")
    private String avatar;

}
