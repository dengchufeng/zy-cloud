package com.zy.common.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zy.common.core.constant.OftenConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.Date;

/**
 * 元对象处理器
 *
 * @Author: xc
 * @Date: 2023/9/8 11:05
 */
@ConditionalOnProperty(name = "mybatis-plus.fill", havingValue = "true")
public class MybatisMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增时自动填充规则
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 单据状态：默认创建（A）
        this.strictInsertFill(metaObject, OftenConstant.BILL_STATUS, () -> OftenConstant.A, String.class);
        // 创建时间：默认当前日期
        this.strictInsertFill(metaObject, OftenConstant.CREATE_DATE, Date::new, Date.class);
    }

    /**
     * 修改时自动填充规则
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 修改时间：默认当前日期
        this.strictUpdateFill(metaObject, OftenConstant.MODIFY_DATE, Date::new, Date.class);
    }

}
