package com.xiong.user.interceptor;

import com.xiong.common.util.HttpResponseUtil;
import com.xiong.common.util.R;
import com.xiong.common.util.SmsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 账号注册拦截器
 */
public class RegisterInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("POST".equals(request.getMethod()) && "/user/register".equals(request.getServletPath())){
            String registerCode = request.getParameter("register_code").trim();
            String targetCode = redisTemplate.opsForValue().get(SmsConstants.SMS_PERFIX + request.getParameter("username"));
            if (StringUtils.isEmpty(registerCode)){
                HttpResponseUtil.output(response, R.error("注册码不能为空"));
                return false;
            }else if(StringUtils.isEmpty(targetCode)){
                HttpResponseUtil.output(response, R.error("注册码已过期"));
                return false;
            }else if (!targetCode.equals(registerCode)){
                HttpResponseUtil.output(response, R.error("注册码填写错误"));
                return false;
            }else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        redisTemplate.delete(SmsConstants.SMS_PERFIX +request.getParameter("username"));
    }
}
