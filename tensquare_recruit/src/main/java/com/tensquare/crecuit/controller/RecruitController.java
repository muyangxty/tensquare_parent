package com.tensquare.crecuit.controller;

import java.util.List;
import java.util.Map;

import entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.tensquare.crecuit.pojo.Recruit;
import com.tensquare.crecuit.service.RecruitService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

/**
 * 职位相关控制器层
 *
 * @author MuYang
 * @date 2020/1/7
 */
@RestController
@CrossOrigin
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    /**
     * 查询推荐职位
     *
     * @return
     */
    @GetMapping("/search/recommend")
    public Result findRecommend() {
        List<Recruit> recommend = recruitService.recommend();
        return new Result(true, StatusCode.OK, Message.SUCCESS, recommend);
    }

    /**
     * 查询最新职位
     *
     * @return
     */
    @GetMapping("/search/newlist")
    public Result findNewPosition() {
        List<Recruit> recruits = recruitService.newPosition();
        return new Result(true, StatusCode.OK, Message.SUCCESS, recruits);
    }


    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping()
    public Result findAll() {
        return new Result(true, StatusCode.OK, Message.SUCCESS, recruitService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, Message.SUCCESS, recruitService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Recruit> pageList = recruitService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, Message.SUCCESS, new PageResult<Recruit>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, Message.SUCCESS, recruitService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param recruit
     */
    @PostMapping()
    public Result add(@RequestBody Recruit recruit) {
        recruitService.add(recruit);
        return new Result(true, StatusCode.OK, Message.SAVE_SUCCESS);
    }

    /**
     * 修改
     *
     * @param recruit
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Recruit recruit, @PathVariable String id) {
        recruit.setId(id);
        recruitService.update(recruit);
        return new Result(true, StatusCode.OK, Message.UPDATE_SUCCESS);
    }

    /**
     * 删除
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        recruitService.deleteById(id);
        return new Result(true, StatusCode.OK, Message.DELETE_SUCCESS);
    }

}
