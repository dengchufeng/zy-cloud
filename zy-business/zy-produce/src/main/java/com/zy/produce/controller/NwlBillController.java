package com.zy.produce.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.common.core.constant.OftenConstant;
import com.zy.common.core.utils.ResultUtils;
import com.zy.common.core.vo.operation.AuditVo;
import com.zy.produce.entity.nwlbill.NwlBill;
import com.zy.produce.service.nwlbill.INwlBillEntryService;
import com.zy.produce.service.nwlbill.INwlBillService;
import com.zy.produce.vo.nwlbill.NwlBillEntryVo;
import com.zy.produce.vo.nwlbill.NwlBillSearchVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 多单据 控制器
 *
 * @Author xc
 * @Date 2023/09/14 15:29
 */
@RestController
@RequestMapping("/nwlBill")
public class NwlBillController {

    private final INwlBillService nwlBillService;
    private final INwlBillEntryService nwlBillEntryService;

    public NwlBillController(INwlBillService nwlBillService, INwlBillEntryService nwlBillEntryService) {
        this.nwlBillService = nwlBillService;
        this.nwlBillEntryService = nwlBillEntryService;
    }


    /**
     * 多单据列表
     *
     * @param search 搜索参数
     */
    @GetMapping(value = "/list")
    public ResultUtils<Page<NwlBill>> nwlBillList(NwlBillSearchVo search) {
        Page<NwlBill> page = nwlBillService.nwlBillList(search);
        return ResultUtils.ok(page);
    }

    /**
     * 多单据明细列表
     *
     * @param fid 多单据id
     */
    @GetMapping(value = "/entryList")
    public ResultUtils<List<NwlBillEntryVo>> entryList(String fid) {
        List<NwlBillEntryVo> dataList = nwlBillEntryService.entryList(fid);
        return ResultUtils.ok(dataList);
    }

    /**
     * 多单据添加
     *
     * @param nwlBill 多单据参数
     */
    @PostMapping(value = "/add")
    public ResultUtils<NwlBill> add(@Validated @RequestBody NwlBill nwlBill) {
        nwlBillService.add(nwlBill);
        return ResultUtils.ok(nwlBill);
    }

    /**
     * 多单据编辑
     *
     * @param nwlBill 多单据参数
     */
    @PutMapping(value = "/edit")
    public ResultUtils<NwlBill> edit(@Validated @RequestBody NwlBill nwlBill) {
        nwlBillService.edit(nwlBill);
        return ResultUtils.ok(nwlBill);
    }

    /**
     * 多单据根据id删除
     *
     * @param id 多单据id
     */
    @DeleteMapping(value = "/delete")
    public ResultUtils<String> delete(@RequestParam(name = "id") String id) {
        nwlBillService.delete(id);
        return ResultUtils.ok(OftenConstant.DELETE_SUCCESS);
    }

    /**
     * 多单据批量删除
     *
     * @param ids 多单据id，多个用英文逗号分隔
     */
    @DeleteMapping(value = "/deleteBatch")
    public ResultUtils<String> deleteBatch(@RequestParam(name = "ids") String ids) {
        nwlBillService.deleteBatch(ids);
        return ResultUtils.ok(OftenConstant.BATCH_DELETE_SUCCESS);
    }

    /**
     * 多单据根据id查询
     *
     * @param id 多单据id
     */
    @GetMapping(value = "/queryById")
    public ResultUtils<NwlBill> queryById(@RequestParam(name = "id") String id) {
        NwlBill nwlBill = nwlBillService.getById(id);
        return ResultUtils.ok(nwlBill);
    }

    /**
     * 多单据修改审核状态
     *
     * @param auditVo 审核状态参数
     */
    @PutMapping(value = "/audit")
    public ResultUtils<String> updateAuditStatus(@Validated @RequestBody AuditVo auditVo) {
        nwlBillService.updateAuditStatus(auditVo);
        return ResultUtils.ok();
    }

    /**
     * 多单据批量修改审核状态
     *
     * @param auditVo 审核状态参数
     */
    @PutMapping(value = "/batchAudit")
    public ResultUtils<String> updateAuditStatusBatch(@Validated @RequestBody AuditVo auditVo) {
        nwlBillService.updateAuditStatusBatch(auditVo);
        return ResultUtils.ok();
    }

}
