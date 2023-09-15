package com.zy.common.core.constant.http;

/**
 * 状态码常数类
 *
 * @Author: xc
 * @Date: 2023/9/4 16:44
 */
public class StatusConstant {

    private StatusConstant() {
    }

    public static final Integer OK = 200;                       // 操作成功
    public static final Integer PARAM_ERROR = 400;              // 参数错误
    public static final Integer ERROR = 500;                    // 系统内部错误

}
