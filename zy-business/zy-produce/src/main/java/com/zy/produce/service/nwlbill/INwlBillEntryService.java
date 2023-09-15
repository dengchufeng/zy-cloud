package com.zy.produce.service.nwlbill;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.produce.entity.nwlbill.NwlBillEntry;
import com.zy.produce.vo.nwlbill.NwlBillEntryVo;

import java.util.List;

/**
 * 多单据明细 服务类
 *
 * @Author xc
 * @Date 2023/09/14 15:16
 */
public interface INwlBillEntryService extends IService<NwlBillEntry> {

    /**
     * 多单据明细列表
     *
     * @param fid 表头id
     * @return List
     */
    List<NwlBillEntryVo> entryList(String fid);

}
