package com.xiong.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务逻辑异常
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends RuntimeException {

    private Integer code;
    private String msg;

    public ServiceException(String msg) {
        this.msg = msg;
    }
}
