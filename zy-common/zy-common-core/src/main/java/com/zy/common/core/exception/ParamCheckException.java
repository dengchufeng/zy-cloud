package com.zy.common.core.exception;

import com.zy.common.core.constant.http.StatusConstant;
import lombok.Getter;

/**
 * 参数验证异常类
 *
 * @Author: xc
 * @Date: 2023/9/5 16:10
 */
@Getter
public class ParamCheckException extends RuntimeException {

    // 一参构造器
    public ParamCheckException(String msg) {
        this.code = StatusConstant.PARAM_ERROR;
        this.msg = msg;
    }

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 消息内容
     */
    private final String msg;

}
