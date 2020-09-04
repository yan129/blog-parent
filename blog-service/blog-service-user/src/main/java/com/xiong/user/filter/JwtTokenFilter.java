package com.xiong.user.filter;

import com.xiong.common.util.HttpResponseUtil;
import com.xiong.common.util.ResponseCode;
import com.xiong.user.util.JwtConstant;
import com.xiong.user.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头的token
        String authHeader = request.getHeader(JwtConstant.HEADER_AUTH);
        if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith(JwtConstant.TOKEN_PREFIX)){
            // 截取token
            String authToken = authHeader.substring(JwtConstant.TOKEN_PREFIX.length());
            // 解析token获取用户名
            String username = jwtUtil.extractUsername(authToken);
            if (!StringUtils.isEmpty(username) && StringUtils.isEmpty(SecurityContextHolder.getContext().getAuthentication())){
                // 登录操作
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 判断账户是否可用
                if (!userDetails.isEnabled()){
                    HttpResponseUtil.output(response, ResponseCode.USER_ACCOUNT_DISABLE);
                    return;
                }else if (!userDetails.isAccountNonExpired()){
                    HttpResponseUtil.output(response, ResponseCode.USER_ACCOUNT_EXPIRED);
                    return;
                }else if (!userDetails.isAccountNonLocked()){
                    HttpResponseUtil.output(response, ResponseCode.USER_ACCOUNT_LOCKED);
                    return;
                }else if(!userDetails.isCredentialsNonExpired()){
                    HttpResponseUtil.output(response, ResponseCode.USER_CREDENTIALS_EXPIRED);
                    return;
                } else if (jwtUtil.validateToken(authToken, userDetails)){ // 校验token
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
