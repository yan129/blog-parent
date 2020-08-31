package com.xiong.common.service;

import com.xiong.common.util.R;

import javax.servlet.http.HttpServletRequest;

public interface IdempotentTokenService {

    R createToken();

    void checkToken(HttpServletRequest request);
}
