package com.xiong.sms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiong.common.exception.ServiceException;
import com.xiong.sms.service.SmsService;
import com.xiong.common.util.RandomUtil;
import com.xiong.common.util.SmsConstants;
import com.zhenzi.sms.ZhenziSmsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    @Value("${zhenzi.apiUrl}")
    private String apiUrl;  // 个人开发者请求地址

    @Value("${zhenzi.appId}")
    private String appId;

    @Value("${zhenzi.appSecret}")
    private String appSecret;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean send(String phone) {
        boolean flag = false;
        try {
            String registerCode = RandomUtil.getSixBitRandom();
            // 发送短信
            ZhenziSmsClient smsClient = new ZhenziSmsClient(apiUrl, appId, appSecret);
            // 剩余短信条数余额信息
            String balance = smsClient.balance();
            // 还剩10条通知管理员
            if ("10".equals(balance)){
                log.error("短信剩余数量：" + balance + "条");
            }
            if ("0".equals(balance)){
                log.error("短信剩余数量：" + balance + "条");
                return flag;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String message = "您的注册码为:"+ registerCode +"，该码有效期为5分钟，该码只能使用一次!";
            Map<String, String> params = new HashMap<>();
            params.put("message", message);
            params.put("number", phone);
            String resultMsg = smsClient.send(params);
            // 解析resultMsg返回的json数据，并放入Map
            Map<String, Object> readValue = objectMapper.readValue(resultMsg, Map.class);
            // 获取发送短信code: 发送状态，0为成功。非0为发送失败
            Integer status = (Integer) readValue.get("code");
            if (status == 0){
                // 发送成功将注册码缓存到Redis
                redisTemplate.opsForValue().set(SmsConstants.SMS_PERFIX + phone, registerCode, SmsConstants.SMS_EXPIRE_TIME, TimeUnit.SECONDS);
                flag = true;
            }else {
                log.error("fail：短信服务异常");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException("短信服务异常");
        }
        return flag;
    }
}
