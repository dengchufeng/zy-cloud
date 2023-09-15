package com.zy.produce.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Feign测试：Nacos不同命名空间
 *
 * @Author: xc
 * @Date: 2023/8/22 9:23
 */
@FeignClient("zy-base")
public interface FeignTest {

    @GetMapping("/hello")
    String hello();

}
