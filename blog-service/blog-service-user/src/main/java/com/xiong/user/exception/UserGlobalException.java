package com.xiong.user.exception;

import com.xiong.common.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserGlobalException {

    @ExceptionHandler(UsernameNotFoundException.class)
    public R usernameNotFound(UsernameNotFoundException e){
        log.error("UsernameNotFoundException: {}", e.getMessage());
        return R.error(e.getMessage());
    }
}
