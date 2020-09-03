package com.xiong.sms.controller;

import com.xiong.common.util.R;
import com.xiong.common.util.ResponseCode;
import com.xiong.sms.service.SmsService;
import com.xiong.sms.util.SmsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/send/{phone}")
    public R sendSms(@PathVariable String phone){
        boolean regex = Pattern.compile("^[1][34578]\\d{9}$").matcher(phone.trim()).matches();
        if (regex){
            // 从redis获取验证码，如果获取到直接返回
            if (redisTemplate.hasKey(SmsConstants.SMS_PERFIX + phone)){
                return R.error(ResponseCode.REPETITIVE_OPERATION.getMsg());
            }
            //手机号校验成功发送注册码
            return smsService.send(phone) ? R.success("发送成功") : R.error("发送失败");
        }
        return R.error("请输入正确手机号");
    }
}
