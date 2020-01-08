package com.tensquares.spit.controller;

import com.tensquares.spit.pojo.Spit;
import com.tensquares.spit.service.ISpitService;

import entity.Message;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 吐槽相关的控制器层
 *
 * @author MuYang
 * @date 2020/1/8
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private ISpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询全部吐槽
     *
     * @return
     */
    @GetMapping()
    public Result findAll() {
        List<Spit> spits = this.spitService.findAll();
        if (CollectionUtils.isEmpty(spits)) {
            return new Result(false, StatusCode.ERROR, Message.NONE_RESULT);
        }
        return new Result(true, StatusCode.OK, Message.SUCCESS, spits);
    }

    /**
     * 根据id，查询吐槽
     *
     * @param spitId id
     * @return
     */
    @GetMapping("/{spitId}")
    public Result findSpitBySpitId(@PathVariable("spitId") String spitId) {
        Spit spit = this.spitService.findById(spitId);
        return new Result(true, StatusCode.OK, Message.SUCCESS, spit);
    }

    /**
     * 新增吐槽
     *
     * @param spit
     * @return
     */
    @PostMapping()
    public Result save(@RequestBody Spit spit) {
        this.spitService.save(spit);
        return new Result(true, StatusCode.OK, Message.SAVE_SUCCESS);
    }

    /**
     * 修改吐槽
     *
     * @param spitId
     * @param spit
     * @return
     */
    @PutMapping("/{spitId}")
    public Result updateSpitBySpitId(@PathVariable("spitId") String spitId, @RequestBody Spit spit) {
        spit.set_id(spitId);
        this.spitService.update(spit);
        return new Result(true, StatusCode.OK, Message.UPDATE_SUCCESS);
    }

    /**
     * 删除吐槽
     *
     * @param spitId
     * @return
     */
    @DeleteMapping("/{spitId}")
    public Result deleteSpit(@PathVariable("spitId") String spitId) {
        this.spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK, Message.DELETE_SUCCESS);
    }

    /**
     * 吐槽点赞
     *
     * @param spitId 吐槽id
     * @return
     */
    @PutMapping("/thumbup/{spitId}")
    public Result addThumbup(@PathVariable("spitId") String spitId) {
        String userid = "111";
        //判断当前用户是否已经点赞
        if (redisTemplate.opsForValue().get("thumbup_" + userid) != null){
            return new Result(false, StatusCode.REPERROR, "不能重复点赞");
        }
        this.spitService.addThumbup(spitId);
        //将已点赞的用户id,存入redis缓存
        redisTemplate.opsForValue().set("thumbup_" + userid, 1);
        return new Result(true, StatusCode.OK, Message.SAVE_SUCCESS);
    }

    /**
     * 分页查询吐槽
     *
     * @param spit 吐槽
     * @param page 页码
     * @param size 每页显示记录数
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Spit spit, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Page<Spit> pageLabel = this.spitService.pageQuery(spit, page, size);
        return new Result(true, StatusCode.OK, Message.SAVE_SUCCESS, new PageResult<>(pageLabel.getTotalElements(), pageLabel.getContent()));
    }

    /**
     * 根据上级id,查询吐槽数据
     *
     * @param parentid 上级id
     * @param page     页码
     * @param size     每页记录数
     * @return
     */
    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result QuerySpitByParentId(@PathVariable("parentid") String parentid, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Page<Spit> pageLabel = this.spitService.QuerySpitByParentId(parentid, page, size);
        return new Result(true, StatusCode.OK, Message.SUCCESS, new PageResult<Spit>(pageLabel.getTotalElements(), pageLabel.getContent()));

    }

}
