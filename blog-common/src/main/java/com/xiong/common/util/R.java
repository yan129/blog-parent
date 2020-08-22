package com.xiong.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回统一实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> implements Serializable {

    private Integer status;
    private String msg;
    private T data;

    public R(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public R(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    public static R success(){
        return new R(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
    }

    public static R success(String msg){
        return new R(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> R success(T data){
        return new R(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> R success(String msg, T data){
        return new R(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static R error(){
        return new R(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg());
    }

    public static R error(String msg){
        return new R(ResponseCode.ERROR.getCode(), msg);
    }

    public static <T> R error(T data){
        return new R(ResponseCode.ERROR.getCode(), data);
    }

    public static <T> R error(String msg, T data){
        return new R(ResponseCode.ERROR.getCode(), msg, data);
    }

}
