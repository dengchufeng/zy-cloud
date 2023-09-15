package com.zy.common.redis.service;

import com.zy.common.redis.constant.RedisConstant;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Redis 服务类
 *
 * @Author: xc
 * @Date: 2023/9/12 14:23
 */
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    /**
     * 设置字符串键值对
     *
     * @param key   键
     * @param value 值
     */
    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置字符串键值对，默认单位为秒
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间
     */
    public void setString(String key, String value, long timeout) {
        setString(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置字符串键值对，并可以设置过期时间
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间
     * @param unit    过期时间单位
     */
    public void setString(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 获取字符串键的值
     *
     * @param key 键
     * @return 键对应的字符串值，如果键不存在则返回null
     */
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 递增字符串键的计数，默认步长为1
     *
     * @param key 键
     * @return 递增后的计数值
     */
    public Long incrementString(String key) {
        return incrementString(key, 1L);
    }

    /**
     * 递增字符串键的计数，指定步长
     *
     * @param key   键
     * @param delta 递增或递减的步长
     * @return 递增或递减后的计数值
     */
    public Long incrementString(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 根据前缀生成编码
     *
     * @param prefix 前缀
     * @return 生成的编码，如果前缀不能为null，则抛出异常
     */
    public String generateCode(String prefix) {
        return generateCode(prefix, "");
    }

    /**
     * 根据前缀和后缀生成编码
     *
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 生成的编码，如果前缀或后缀为null，则抛出异常
     */
    public String generateCode(String prefix, String suffix) {
        Objects.requireNonNull(prefix, RedisConstant.PREFIX_NOT_NULL);
        Objects.requireNonNull(suffix, RedisConstant.SUFFIX_NOT_NULL);
        // 获取当前日期，并格式化为年月日的字符串
        LocalDate currentDate = LocalDate.now();
        String dateString = currentDate.format(DateTimeFormatter.ofPattern(RedisConstant.YYYY_MM_DD));
        // 拼接前缀、日期和后缀生成编码
        String code = prefix + dateString + suffix;
        // 递增字符串键的计数
        Long codeIncrement = incrementString(code);
        return code + codeIncrement;
    }

}
