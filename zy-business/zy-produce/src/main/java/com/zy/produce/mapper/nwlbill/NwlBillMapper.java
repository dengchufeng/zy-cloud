package com.zy.produce.mapper.nwlbill;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.produce.entity.nwlbill.NwlBill;
import com.zy.produce.vo.nwlbill.NwlBillSearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 多单据 Mapper 接口
 *
 * @Author xc
 * @Date 2023/09/14 14:27
 */
@Mapper
public interface NwlBillMapper extends BaseMapper<NwlBill> {

    /**
     * 多单据列表
     *
     * @param page   分页参数
     * @param search 搜索参数
     * @return List
     **/
    List<NwlBill> nwlBillList(Page<NwlBill> page, @Param("search") NwlBillSearchVo search);

}
