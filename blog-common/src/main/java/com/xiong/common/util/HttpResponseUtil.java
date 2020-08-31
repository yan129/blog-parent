package com.xiong.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpResponseUtil {
    /**
     * HTTP数据返回
     * @param response  HTTP响应体
     * @param data  返回数据
     * @throws IOException
     */
    public static void output(HttpServletResponse response, Object data) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(data));
        writer.flush();
        writer.close();
    }
}
