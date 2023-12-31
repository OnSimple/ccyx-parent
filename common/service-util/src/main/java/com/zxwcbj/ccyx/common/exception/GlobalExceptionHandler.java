package com.zxwcbj.ccyx.common.exception;

import com.zxwcbj.ccyx.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//异常处理器.当你出现这个异常时调用下面的方法
    @ResponseBody//返回json数据
    public Result error(Exception exception) {
        exception.printStackTrace();
        return Result.fail(null);
    }

    /**
     * 自定义异常处理方法
     *
     * @param ccxException
     * @return
     */
    @ExceptionHandler(CcyxException.class)
    @ResponseBody
    private Result error(CcyxException ccxException) {
        return Result.build(null, ccxException.getCode(), ccxException.getMessage());
    }
}
