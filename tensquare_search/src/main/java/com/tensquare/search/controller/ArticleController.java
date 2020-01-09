package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import entity.Message;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author MuYang
 * @date 2020/1/9
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    @PostMapping("")
    public Result save(@RequestBody Article article) {
        articleService.save(article);
        return new Result(true, StatusCode.OK, Message.SAVE_SUCCESS);
    }

    /**
     * 分页搜索
     *
     * @param key  搜索字段
     * @param page 页码
     * @param size 每页记录数
     * @return
     */
    @GetMapping("/search/{key}/{page}/{size}")
    public Result findBykey(
            @PathVariable("key") String key,
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size) {
        Page<Article> pageResult = articleService.findBykey(key, page, size);

        if (CollectionUtils.isEmpty(pageResult.getContent())) {
            return new Result(false, StatusCode.ERROR, Message.NONE_RESULT);
        }
        return new Result(true, StatusCode.OK, Message.SUCCESS, new PageResult<Article>(pageResult.getTotalElements(), pageResult.getContent()));
    }

}
