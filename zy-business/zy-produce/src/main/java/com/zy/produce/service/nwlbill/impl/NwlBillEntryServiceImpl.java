package com.zy.produce.service.nwlbill.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.produce.entity.nwlbill.NwlBillEntry;
import com.zy.produce.mapper.nwlbill.NwlBillEntryMapper;
import com.zy.produce.service.nwlbill.INwlBillEntryService;
import com.zy.produce.vo.nwlbill.NwlBillEntryVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 多单据明细 服务实现类
 *
 * @Author xc
 * @Date 2023/09/14 15:20
 */
@Service
public class NwlBillEntryServiceImpl extends ServiceImpl<NwlBillEntryMapper, NwlBillEntry> implements INwlBillEntryService {

    /**
     * 多单据明细列表
     *
     * @param fid 表头id
     * @return List
     */
    @Override
    public List<NwlBillEntryVo> entryList(String fid) {
        return this.baseMapper.entryList(fid);
    }

}
