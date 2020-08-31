package com.xiong.user.config;

import com.xiong.common.util.HttpResponseUtil;
import com.xiong.common.util.R;
import com.xiong.common.util.ResponseCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理器
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        SecurityUser user = (SecurityUser) auth.getPrincipal();
//        String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
//        redisTemplate.opsForValue().set(user.getCurrentUserInfo().getUsername(), user.getPermissionValueList());
//
//        ResponseUtil.out(res, R.ok().data("token", token));
        R success = R.success(ResponseCode.USER_LOGIN_SUCCESS.getMsg());
        HttpResponseUtil.output(response, success);
    }
}
