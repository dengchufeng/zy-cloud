package com.zy.base.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.common.core.utils.ResultUtils;
import com.zy.common.core.vo.operation.AuditVo;
import com.zy.common.core.constant.OftenConstant;
import com.zy.base.entity.nwlbase.NwlBase;
import com.zy.base.service.nwlbase.INwlBaseService;
import com.zy.base.vo.nwlbase.NwlBaseSearchVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 单测试 控制器
 *
 * @Author xc
 * @Date 2023/09/13 09:15
 */
@RestController
@RequestMapping("/nwlBase")
public class NwlBaseController {

    private final INwlBaseService nwlBaseService;

    public NwlBaseController(INwlBaseService nwlBaseService) {
        this.nwlBaseService = nwlBaseService;
    }


    /**
     * 单测试列表
     *
     * @param search 搜索参数
     */
    @GetMapping(value = "/list")
    public ResultUtils<Page<NwlBase>> nwlBaseList(NwlBaseSearchVo search) {
        Page<NwlBase> page = nwlBaseService.nwlBaseList(search);
        return ResultUtils.ok(page);
    }

    /**
     * 单测试添加
     *
     * @param nwlBase 单测试参数
     */
    @PostMapping(value = "/add")
    public ResultUtils<NwlBase> add(@Validated @RequestBody NwlBase nwlBase) {
        nwlBaseService.add(nwlBase);
        return ResultUtils.ok(nwlBase);
    }

    /**
     * 单测试编辑
     *
     * @param nwlBase 单测试对象
     */
    @PutMapping(value = "/edit")
    public ResultUtils<NwlBase> edit(@Validated @RequestBody NwlBase nwlBase) {
        nwlBaseService.edit(nwlBase);
        return ResultUtils.ok(nwlBase);
    }

    /**
     * 单测试根据id删除
     *
     * @param id 单测试id
     */
    @DeleteMapping(value = "/delete")
    public ResultUtils<String> delete(@RequestParam(name = "id") String id) {
        nwlBaseService.delete(id);
        return ResultUtils.ok(OftenConstant.DELETE_SUCCESS);
    }

    /**
     * 单测试批量删除
     *
     * @param ids 单测试id，多个用英文逗号分隔
     */
    @DeleteMapping(value = "/deleteBatch")
    public ResultUtils<String> deleteBatch(@RequestParam(name = "ids") String ids) {
        nwlBaseService.deleteBatch(ids);
        return ResultUtils.ok(OftenConstant.BATCH_DELETE_SUCCESS);
    }

    /**
     * 单测试根据id查询
     *
     * @param id 单测试id
     */
    @GetMapping(value = "/queryById")
    public ResultUtils<NwlBase> queryById(@RequestParam(name = "id") String id) {
        NwlBase nwlBase = nwlBaseService.getById(id);
        return ResultUtils.ok(nwlBase);
    }

    /**
     * 单测试修改审核状态
     *
     * @param auditVo 审核状态参数
     */
    @PutMapping(value = "/audit")
    public ResultUtils<String> updateAuditStatus(@Validated @RequestBody AuditVo auditVo) {
        nwlBaseService.updateAuditStatus(auditVo);
        return ResultUtils.ok();
    }

    /**
     * 单测试批量修改审核状态
     *
     * @param auditVo 审核状态参数
     */
    @PutMapping(value = "/batchAudit")
    public ResultUtils<String> updateAuditStatusBatch(@Validated @RequestBody AuditVo auditVo) {
        nwlBaseService.updateAuditStatusBatch(auditVo);
        return ResultUtils.ok();
    }

}
