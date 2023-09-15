package com.zy.base.mapper.nwlbase;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.base.entity.nwlbase.NwlBase;
import com.zy.base.vo.nwlbase.NwlBaseSearchVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 单测试 Mapper 接口
 *
 * @Author xc
 * @Date 2023/09/09 16:33
 */
@Mapper
public interface NwlBaseMapper extends BaseMapper<NwlBase> {

    /**
     * 单测试列表
     *
     * @param page   分页参数
     * @param search 搜索参数
     * @return List
     **/
    List<NwlBase> nwlBaseList(Page<NwlBase> page, @Param("search") NwlBaseSearchVo search);

}
