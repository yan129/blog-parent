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

//    public static R success(){
//        R r = new R();
//        r.setStatus(ResponseCode.SUCCESS.getCode());
//        r.setMsg(ResponseCode.SUCCESS.getMsg());
//        return r;
//    }
//
//    public static R error(){
//        R r = new R();
//        r.setStatus(ResponseCode.ERROR.getCode());
//        r.setMsg(ResponseCode.ERROR.getMsg());
//        return r;
//    }
//
//    public R msg(String msg){
//        this.setMsg(msg);
//        return this;
//    }
//
//    public R data(T data){
//        this.setData(data);
//        return this;
//    }

    public R(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public R(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    public R(T data) {
        this.data = data;
    }

    public static <T> R<T> none(T data){
        return new R(data);
    }

    public static R success(){
        return new R(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
    }

    public static R success(String msg){
        return new R(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> success(T data){
        return new R(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> R<T> success(String msg, T data){
        return new R(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static R error(){
        return new R(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg());
    }

    public static R error(String msg){
        return new R(ResponseCode.ERROR.getCode(), msg);
    }

    public static <T> R<T> error(T data){
        return new R(ResponseCode.ERROR.getCode(), data);
    }

    public static <T> R<T> error(String msg, T data){
        return new R(ResponseCode.ERROR.getCode(), msg, data);
    }

}
