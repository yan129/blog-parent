package com.xiong.user.controller;


import com.xiong.common.util.R;
import com.xiong.user.service.UserService;
import com.xiong.user.vo.LoginVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xiong
 * @since 2020-08-28
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public R register(@RequestBody @Valid LoginVo loginVo, BindingResult errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return R.error(message);
        }
        return userService.register(loginVo);
    }

    @ApiOperation(value = "获取token")
    @PostMapping("/aa")
    public R createAuthenticationToken(){
        return R.success();
    }

    @GetMapping("/bb")
    public R bb(){
        return R.success();
    }
}
