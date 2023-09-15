package com.zy.monitor.controller;

import com.zy.common.pure.utils.VersionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查看项目版本号
 *
 * @Author: xc
 * @Date: 2023/9/14 16:24
 */
@RestController
public class VersionController {

    @Value("${spring.profiles.active}")
    private String active;

    @Value("${pom.version}")
    private String version;

    @GetMapping("/hello")
    public String hello() {
        return VersionUtils.versionContent(active, version);
    }

}
