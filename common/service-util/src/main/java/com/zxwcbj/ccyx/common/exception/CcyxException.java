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
/**
 * 通过状态码和错误消息创建异常对象
 *
 * @param code
 * @param message
 * @return
 **/

public CcyxException(Integer code, String message){
        super(message);
        this.code = code;
    }
        /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public CcyxException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code=resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "ccyxException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
