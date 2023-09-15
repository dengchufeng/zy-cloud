package com.zy.common.core.utils;

import com.zy.common.core.constant.OftenConstant;
import com.zy.common.core.constant.http.StatusConstant;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回结果类
 *
 * @Author: xc
 * @Date: 2023/9/4 16:33
 */
@Getter
@Setter
public class ResultUtils<T> {

    /**
     * 成功标志
     */
    private boolean success = true;

    /**
     * 文本消息
     */
    private String message = OftenConstant.OPERATION_SUCCESS;

    /**
     * 状态码
     */
    private Integer code = StatusConstant.OK;

    /**
     * 结果对象
     */
    private T result;

    /**
     * 成功：返回默认值
     *
     * @param <T> 泛型
     * @return ResultUtils
     */
    public static <T> ResultUtils<T> ok() {
        return ok(OftenConstant.OPERATION_SUCCESS);
    }

    /**
     * 成功：返回文本
     *
     * @param msg 返回内容
     * @param <T> 泛型
     * @return ResultUtils
     */
    public static <T> ResultUtils<T> ok(String msg) {
        ResultUtils<T> res = new ResultUtils<>();
        res.setMessage(msg);
        return res;
    }

    /**
     * 成功：返回对象
     *
     * @param t   结果对象
     * @param <T> 泛型
     * @return ResultUtils
     */
    public static <T> ResultUtils<T> ok(T t) {
        ResultUtils<T> res = new ResultUtils<>();
        res.setResult(t);
        return res;
    }

    /**
     * 失败：1参
     *
     * @param msg 文本消息
     * @param <T> 泛型
     * @return ResultUtils
     */
    public static <T> ResultUtils<T> error(String msg) {
        return error(msg, StatusConstant.PARAM_ERROR);
    }

    /**
     * 失败：2参
     *
     * @param msg  文本消息
     * @param code 状态码
     * @param <T>  泛型
     * @return ResultUtils
     */
    public static <T> ResultUtils<T> error(String msg, Integer code) {
        ResultUtils<T> res = new ResultUtils<>();
        res.setSuccess(false);
        res.setMessage(msg);
        res.setCode(code);
        return res;
    }

}
