package com.zy.produce.redis;

import com.zy.common.redis.service.RedisService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Redis 测试类
 *
 * @Author: xc
 * @Date: 2023/9/12 14:34
 */
@SpringBootTest
class RedisTest {

    @Autowired
    private RedisService redisService;


    @Test
    @DisplayName("简单测试")
    void simpleTest() {
        String key = "WL";
        String code = redisService.generateCode(key, null);
        System.out.println(code);
        Assertions.assertEquals("WL202309131", code);
    }

}
