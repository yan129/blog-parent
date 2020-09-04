package com.xiong.user.feign;

import com.xiong.common.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "BLOG-SMS")
public interface FeignSmsService {

    // 远程调用SMS服务发送短信的方法，默认等待1秒，得不到报错
    @PostMapping("/sms/send/{phone}")
    R sendSms(@PathVariable("phone") String phone);
}
