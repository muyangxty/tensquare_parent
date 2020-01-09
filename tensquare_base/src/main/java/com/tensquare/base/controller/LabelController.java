package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.ILabelService;

import entity.Message;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签相关的控制器层
 *
 * @author MuYang
 * @date 2020/1/6
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private ILabelService labelService;

    /**
     * 查询全部
     *
     * @return
     */
    @GetMapping()
    public Result findAll() {
        List<Label> labels = labelService.findAll();
        if (CollectionUtils.isEmpty(labels)){
            return new Result(false, StatusCode.ERROR, Message.NONE_RESULT);
        }
        return new Result(true, StatusCode.OK, Message.SUCCESS, labels);
    }

    /**
     * 根据id,查询标签
     *
     * @param labelId 标签id
     * @return
     */
    @GetMapping("/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId) {
        Label label = this.labelService.findById(labelId);
        return new Result(true, StatusCode.OK, Message.SUCCESS, label);
    }

    /**
     * 新增标签
     *
     * @param label 标签数据
     * @return
     */
    @PostMapping()
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result(true, StatusCode.OK, Message.SAVE_SUCCESS);
    }

    /**
     * 修改标签
     *
     * @param labelId 标签id
     * @return
     */
    @PutMapping("update/{labelId}")
    public Result updateById(@PathVariable("labelId") String labelId, @RequestBody Label label) {
        label.setId(labelId);
        labelService.updateById(labelId, label);
        return new Result(true, StatusCode.OK, Message.UPDATE_SUCCESS);
    }

    /**
     * 删除标签
     *
     * @param labelId 标签id
     * @return
     */
    @DeleteMapping("delete/{labelId}")
    public Result deleteById(@PathVariable("labelId") String labelId) {
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, Message.DELETE_SUCCESS);
    }

    /**
     * 搜索标签
     *
     * @param label
     * @return
     */
    @PostMapping("search")
    public Result findSearch(@RequestBody Label label) {
        List<Label> labelList = labelService.findSearch(label);
        //检查查询结果
        if (CollectionUtils.isEmpty(labelList)) {
            return new Result(false, StatusCode.ERROR, Message.NONE_RESULT);
        }
        return new Result(true, StatusCode.OK, Message.SUCCESS, labelList);
    }

    /**
     * 分页查询
     *
     * @param label 标签
     * @param page  页码
     * @param size  每页显示记录数
     * @return
     */
    @PostMapping("search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> pageLabel = labelService.pageQuery(label, page, size);
        if (CollectionUtils.isEmpty(pageLabel.getContent())){
            return new Result(false, StatusCode.ERROR, Message.NONE_RESULT);
        }
        return new Result(true, StatusCode.OK, Message.SUCCESS, new PageResult<>(pageLabel.getTotalElements(), pageLabel.getContent()));
    }

}
