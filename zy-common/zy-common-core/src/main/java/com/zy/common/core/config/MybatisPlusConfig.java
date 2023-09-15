package com.zy.common.core.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.zy.common.core.constant.DbConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * 配置分页插件
 *
 * @Author: xc
 * @Date: 2023/9/4 14:59
 */
public class MybatisPlusConfig {

    @Value("${mybatis-plus.dbType}")
    private String dbType;


    /**
     * 添加分页插件
     *
     * @return 包含分页功能的 MybatisPlusInterceptor 对象
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(createPaginationInnerInterceptor());
        return interceptor;
    }

    /**
     * 获取数据库类型
     *
     * @return 根据数据库类型创建对应的 PaginationInnerInterceptor 对象
     */
    private PaginationInnerInterceptor createPaginationInnerInterceptor() {
        return switch (dbType) {
            case DbConstant.MYSQL -> new PaginationInnerInterceptor(DbType.MYSQL);
            case DbConstant.SQL_SERVER_2005 -> new PaginationInnerInterceptor(DbType.SQL_SERVER2005);
            default -> new PaginationInnerInterceptor();
        };
    }

}
