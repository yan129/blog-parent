package com.xiong.user.util;

public class JwtConstant {

    // Token前缀
    public static final String TOKEN_PREFIX = "Bearer ";

    // 存放Token的Header Key
    public static final String HEADER_AUTH = "Authorization";

    // token过期时间
    public static final long EXPIRATION_TIME = 1000 * 60;    // 60秒
}
