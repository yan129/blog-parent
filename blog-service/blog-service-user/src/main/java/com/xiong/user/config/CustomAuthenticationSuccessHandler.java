package com.xiong.user.config;

import com.xiong.common.util.HttpResponseUtil;
import com.xiong.common.util.R;
import com.xiong.common.util.ResponseCode;
import com.xiong.user.entity.User;
import com.xiong.user.service.UserService;
import com.xiong.user.util.JwtConstant;
import com.xiong.user.util.JwtUtil;
import com.xiong.user.util.RsaKeyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理器
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User principal = ((User) authentication.getPrincipal());
        User user = new User();
        user.setId(principal.getId());
        user.setEnabled(principal.isEnabled());
        user.setLastLoginTime(new Date());
        userService.updateById(user);
//        redisTemplate.opsForValue().set(principal.getUsername(), String.valueOf(principal.getRoles()));
        Map<String, String> map = new HashMap<>();
        String token = jwtUtil.generateToken(((UserDetails) authentication.getPrincipal()));
        map.put("token", JwtConstant.TOKEN_PREFIX + token);
        R success = R.success(ResponseCode.USER_LOGIN_SUCCESS.getMsg());
        success.setData(map);
        HttpResponseUtil.output(response, success);
    }
}
