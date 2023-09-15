package com.zy.common.redis.constant;

/**
 * Redis 常数类
 *
 * @Author: xc
 * @Date: 2023/9/13 10:52
 */
public class RedisConstant {

    private RedisConstant() {
    }

    // 日期
    public static final String YYYY_MM_DD = "yyyyMMdd";

    // 提示
    public static final String NOT_NULL = "不能为null：by RedisService.generateCode()";
    public static final String PREFIX_NOT_NULL = "前缀" + NOT_NULL;
    public static final String SUFFIX_NOT_NULL = "后缀" + NOT_NULL;

}
