package com.xiong.common.service.impl;

import com.xiong.common.exception.ServiceException;
import com.xiong.common.service.IdempotentTokenService;
import com.xiong.common.util.R;
import com.xiong.common.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class IdempotentTokenServiceImpl implements IdempotentTokenService {

    private static final String TOKEN_NAME = "token";
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public R createToken() {
        //通过UUID来生成token
        String tokenValue = "idempotent:token" + UUID.randomUUID().toString();
        //将token放入redis中，设置有效期为60S
        redisTemplate.opsForValue().set(tokenValue, "0", 60, TimeUnit.SECONDS);
        return R.success(tokenValue);
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isEmpty(token)){
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isEmpty(token)){
                //没有携带token，抛异常，这里的异常需要全局捕获
                throw new ServiceException(ResponseCode.PARAM_NOT_VALID.getMsg());
            }
        }
        //token不存在，说明token已经被其他请求删除或者是非法的token
        if (!redisTemplate.hasKey(token)){
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
        boolean del = redisTemplate.delete(token);
        if (!del){
            //token删除失败，说明token已经被其他请求删除
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
    }
}
