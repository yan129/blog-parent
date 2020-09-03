package com.xiong.user.config;

import com.xiong.common.util.HttpResponseUtil;
import com.xiong.common.util.R;
import com.xiong.common.util.ResponseCode;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 处理账号被挤下线处理逻辑
 */
@Component
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        R<ResponseCode> error = R.none(ResponseCode.USER_ACCOUNT_BY_OTHERS);
        HttpResponseUtil.output(event.getResponse(), error);
    }
}
