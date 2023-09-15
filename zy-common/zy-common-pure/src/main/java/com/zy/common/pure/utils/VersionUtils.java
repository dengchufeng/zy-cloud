package com.zy.common.pure.utils;

/**
 * 项目版本工具类
 *
 * @Author: xc
 * @Date: 2023/8/26 14:45
 */
public class VersionUtils {

    private VersionUtils() {
    }

    /**
     * 版本内容
     *
     * @param active  运行环境
     * @param version 版本号
     * @return 版本内容组成的字符串
     */
    public static String versionContent(String active, String version) {
        return "当前运行配置为：" + active + "，版本号：" + version;
    }

}
