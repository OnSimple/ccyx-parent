package com.zxwcbj.ccyx.common.result;

import lombok.Data;
/**
 * 统一返回结果
 */
@Data
public class Result<T>{
    //响应码
    private Integer code;
    //消息
    private String message;
    //数据
    private T data;
    private Result() {}
    public static<T> Result<T> build(T data,ResultCodeEnum resultCodeEnum){
        Result<T> result = new Result<>();
        if(data !=null){
            result.setData(data);
        }
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }
    //成功
    public static <T> Result<T> ok(T data) {
        return build(data,ResultCodeEnum.SUCCESS);
    }
    //失败
    public static <T> Result<T> fail(T data) {
        return build(data,ResultCodeEnum.FAIL);
    }

}
