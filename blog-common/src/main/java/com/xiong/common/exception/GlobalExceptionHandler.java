package com.xiong.common.exception;

import com.xiong.common.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public R serviceExceptionHandler(ServiceException se){
        log.error("SE Exception: {}", se.getMsg());
        return R.error(se.getMsg());
    }

}
