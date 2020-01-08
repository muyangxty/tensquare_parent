package com.tensquare.qa.controller;

import java.util.Map;

import entity.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

/**
 * 问答相关的控制器层
 *
 * @author MuYang
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    /**
     * 查询最新回复
     *
     * @param labelid labelid
     * @param page    页码
     * @param size    每页记录数
     * @return
     */
    @GetMapping("/newlist/{labelid}/{page}/{size}")
    public Result findNewListBylabelId(@PathVariable("labelid") String labelid,
                                       @PathVariable("page") Integer page,
                                       @PathVariable("size") Integer size) {
        Page<Problem> problems = this.problemService.newAnswer(labelid, page, size);
        //校验查询结果是否为空
        if (problems == null && problems.getSize() == 0) {
            return new Result(true, StatusCode.OK, Message.NONE_RESULT);
        }
        return new Result(true, StatusCode.OK, Message.SUCCESS, new PageResult<Problem>(problems.getTotalElements(), problems.getContent()));
    }

    /**
     * 查询最热回复
     *
     * @param labelid labelid
     * @param page    页码
     * @param size    每页记录数
     * @return
     */
    @GetMapping("/hotlist/{labelid}/{page}/{size}")
    public Result findHotListBylabelId(@PathVariable("labelid") String labelid,
                                       @PathVariable("page") Integer page,
                                       @PathVariable("size") Integer size) {
        Page<Problem> problems = this.problemService.hotAnswer(labelid, page, size);
        //校验查询结果是否为空
        if (problems == null && problems.getSize() == 0) {
            return new Result(true, StatusCode.OK, Message.NONE_RESULT);
        }
        return new Result(true, StatusCode.OK, Message.SUCCESS, new PageResult<Problem>(problems.getTotalElements(), problems.getContent()));
    }

    /**
     * 查询等待回复
     * @param labelid labelid
     * @param page    页码
     * @param size    每页记录数
     * @return
     */
    @GetMapping("/waitlist/{labelid}/{page}/{size}")
    public Result findWaitListByLabelId(@PathVariable("labelid") String labelid,
                                        @PathVariable("page") Integer page,
                                        @PathVariable("size") Integer size){
        //校验查询结果是否为空
        Page<Problem> problems = this.problemService.waitAnswer(labelid, page, size);
        if (problems == null && problems.getSize() == 0) {
            return new Result(true, StatusCode.OK, Message.NONE_RESULT);
        }
        return new Result(true, StatusCode.OK, Message.SUCCESS, new PageResult<Problem>(problems.getTotalElements(), problems.getContent()));
    }


    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping()
    public Result findAll() {
        return new Result(true, StatusCode.OK, Message.SUCCESS, problemService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, Message.SUCCESS, problemService.findById(id));
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
        Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, Message.SUCCESS, new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, Message.SUCCESS, problemService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param problem
     */
    @PostMapping()
    public Result add(@RequestBody Problem problem) {
        problemService.add(problem);
        return new Result(true, StatusCode.OK, Message.SAVE_SUCCESS);
    }

    /**
     * 修改
     *
     * @param problem
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Problem problem, @PathVariable String id) {
        problem.setId(id);
        problemService.update(problem);
        return new Result(true, StatusCode.OK, Message.UPDATE_SUCCESS);
    }

    /**
     * 删除
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        problemService.deleteById(id);
        return new Result(true, StatusCode.OK, Message.DELETE_SUCCESS);
    }

}
