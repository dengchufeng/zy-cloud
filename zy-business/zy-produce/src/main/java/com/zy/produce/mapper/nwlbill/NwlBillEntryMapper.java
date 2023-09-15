package com.zy.produce.mapper.nwlbill;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.produce.entity.nwlbill.NwlBillEntry;
import com.zy.produce.vo.nwlbill.NwlBillEntryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 多单据明细 Mapper 接口
 *
 * @Author xc
 * @Date 2023/09/14 15:44
 */
@Mapper
public interface NwlBillEntryMapper extends BaseMapper<NwlBillEntry> {

    /**
     * 多单据明细列表
     *
     * @param fid 表头id
     * @return List
     */
    List<NwlBillEntryVo> entryList(@Param("fid") String fid);

}
