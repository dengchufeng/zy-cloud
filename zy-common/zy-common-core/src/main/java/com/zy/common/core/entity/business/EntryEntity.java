package com.zy.common.core.entity.business;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

/**
 * 单据明细通用 实体类
 *
 * @Author: xc
 * @Date: 2023/9/14 14:57
 */
@Getter
@Setter
public class EntryEntity {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 父id
     */
    @TableField("fid")
    private String fid;

}
