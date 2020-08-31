package com.xiong.user.config;

import com.xiong.common.util.HttpResponseUtil;
import com.xiong.common.util.R;
import com.xiong.common.util.ResponseCode;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理器
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        R<ResponseCode> error = R.none(ResponseCode.USER_LOGIN_FAILURE);
        if (exception instanceof LockedException){
            error.setData(ResponseCode.USER_ACCOUNT_LOCKED);
        }else if (exception instanceof CredentialsExpiredException){
            error.setData(ResponseCode.USER_CREDENTIALS_EXPIRED);
        }else if (exception instanceof AccountExpiredException){
            error.setData(ResponseCode.USER_ACCOUNT_EXPIRED);
        }else if(exception instanceof DisabledException){
            error.setData(ResponseCode.USER_ACCOUNT_DISABLE);
        }else if (exception instanceof BadCredentialsException){
            error.setData(ResponseCode.USER_CREDENTIALS_ERROR);
        }
        HttpResponseUtil.output(response, error);
    }
}
