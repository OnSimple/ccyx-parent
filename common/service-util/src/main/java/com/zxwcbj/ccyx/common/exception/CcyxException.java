package com.zxwcbj.ccyx.common.exception;

import com.zxwcbj.ccyx.common.result.ResultCodeEnum;
import lombok.Data;
/**
 * 统一异常处理
 * */
@Data
public class CcyxException extends RuntimeException {
    private Integer code;
    private String message;

    public CcyxException(Integer code, String message) {
        super(message);//使用指定的详细信息消息构造一个新的运行时异常
        this.code =code;
    }
    public CcyxException(ResultCodeEnum resultCode){
        super(resultCode.getMessage());
        this.code=resultCode.getCode();
    }

    @Override
    public String toString() {
        return "CcyxException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
