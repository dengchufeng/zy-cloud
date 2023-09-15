package com.zy.produce.controller;

import com.zy.produce.openfeign.FeignTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feign测试 控制器
 *
 * @Author: xc
 * @Date: 2023/8/22 9:23
 */
@RestController
public class FeignTestController {

    @Autowired
    private FeignTest feignTest;

    @GetMapping("/feignTest")
    public String feignTest() {
        String hello = feignTest.hello();
        return "produce你好呀！zy-base项目" + hello;
    }

}
