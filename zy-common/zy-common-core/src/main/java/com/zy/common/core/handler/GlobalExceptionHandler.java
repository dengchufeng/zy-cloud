package com.zy.common.core.handler;

import com.zy.common.core.constant.OftenConstant;
import com.zy.common.core.constant.http.StatusConstant;
import com.zy.common.core.exception.ParamCheckException;
import com.zy.common.core.utils.ResultUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 全局异常处理器
 *
 * @Author: xc
 * @Date: 2023/9/5 16:32
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public <T> ResultUtils<T> handleException(Exception e) {
        e.printStackTrace();
        return ResultUtils.error(e.getMessage(), StatusConstant.ERROR);
    }

    /**
     * 自定义异常：参数验证异常类
     */
    @ExceptionHandler(ParamCheckException.class)
    public <T> ResultUtils<T> handleParamCheckException(ParamCheckException e) {
        return ResultUtils.error(e.getMsg());
    }

    /**
     * Validated异常：Validated验证异常捕获
     */
    @ExceptionHandler(BindException.class)
    public <T> ResultUtils<T> bindException(BindException e) {
        String errorMessage = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(OftenConstant.COMMA_CN));
        return ResultUtils.error(errorMessage);
    }

}
