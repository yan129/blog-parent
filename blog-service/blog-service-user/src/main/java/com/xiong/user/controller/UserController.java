package com.xiong.user.controller;


import com.xiong.common.util.R;
import com.xiong.user.feign.FeignSmsService;
import com.xiong.user.service.UserService;
import com.xiong.user.util.CaptchConstant;
import com.xiong.user.util.GenerateCaptchaUtil;
import com.xiong.user.vo.LoginVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private FeignSmsService feignSmsService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public R register(@RequestBody @Valid LoginVo loginVo, BindingResult errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return R.error(message);
        }
        return userService.register(loginVo);
    }

    @ApiOperation(value = "发送短信注册")
    @PostMapping("/get_register_cole/{phone}")
    public R createAuthenticationToken(@PathVariable("phone") String phone){
        R sendSms = feignSmsService.sendSms(phone);
        return sendSms;
    }

    @ApiOperation(value = "获取验证码")
    @GetMapping("/get_verify_code")
    public R getVerificationCode(HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setContentType("image/jpeg");
        String uuid = UUID.randomUUID().toString();
        GenerateCaptchaUtil captchaUtil = new GenerateCaptchaUtil();
        BufferedImage bufferedImage = captchaUtil.getImage();
        String text = captchaUtil.getText();
        redisTemplate.opsForValue().set(CaptchConstant.CAPTCH_PERFIX +uuid, text, CaptchConstant.CAPTCH_EXPIRE_TIME, TimeUnit.SECONDS);
        Map<String, Object> map = new HashMap<>();
        map.put("uuid", CaptchConstant.CAPTCH_PERFIX + uuid);
        map.put("img", GenerateCaptchaUtil.output(bufferedImage));
        return R.success(map);
    }
}
