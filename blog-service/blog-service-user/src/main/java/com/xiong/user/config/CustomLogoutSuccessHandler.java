package com.xiong.user.config;

import com.xiong.common.util.HttpResponseUtil;
import com.xiong.common.util.R;
import com.xiong.common.util.ResponseCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出成功处理器
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        R ok = R.none(ResponseCode.USER_ACCOUNT_LOGOUT_SUCCESS);
        HttpResponseUtil.output(response, ok);
    }
}
