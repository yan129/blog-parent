package com.xiong.user.filter;

import com.xiong.common.util.HttpResponseUtil;
import com.xiong.common.util.R;
import com.xiong.user.util.CaptchConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 校验登录验证码
 */
@Component
public class VerificationCodeFilter extends OncePerRequestFilter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("POST".equals(request.getMethod()) && "/user/login".equals(request.getServletPath())){
            String verifyCode = request.getParameter("verify_code").trim();
            String uuid = request.getParameter("uuid");
            String targetCode = redisTemplate.opsForValue().get(uuid);
            if (StringUtils.isEmpty(verifyCode)){
                HttpResponseUtil.output(response, R.error("验证码不能为空"));
                return;
            }else if(StringUtils.isEmpty(targetCode)){
                HttpResponseUtil.output(response, R.error("验证码已过期"));
                return;
            }else if (!targetCode.toLowerCase().equals(verifyCode.toLowerCase())){
                HttpResponseUtil.output(response, R.error("验证码填写错误"));
                return;
            }
            redisTemplate.delete(uuid);
        }
        filterChain.doFilter(request, response);
    }
}
