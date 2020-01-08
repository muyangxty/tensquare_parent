package com.tensquare.article.controller;

import java.util.Map;

import entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

/**
 * 控制器层
 *
 * @author MuYang
 * @date 2020/1/8
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 文章审核
     *
     * @param articleId 文章id
     * @return
     */
    @PutMapping("/examine/{articleId}")
    public Result examine(@PathVariable("articleId") String articleId) {
        this.articleService.articleReview(articleId);
        return new Result(true, StatusCode.OK, Message.EXAMINE_SUCCESS);
    }

    /**
     * 点赞
     *
     * @param articleId 文章id
     * @return
     */
    @PutMapping("/thumbup/{articleId}")
    public Result addThumbupByArticleId(@PathVariable("articleId") String articleId) {
        this.articleService.addThumbupByArticleId(articleId);
        return new Result(true, StatusCode.OK, Message.SAVE_SUCCESS);
    }


    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping()
    public Result findAll() {
        return new Result(true, StatusCode.OK, Message.SUCCESS, articleService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, Message.SUCCESS, articleService.findById(id));
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
        Page<Article> pageList = articleService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, Message.SUCCESS, new PageResult<Article>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, Message.SUCCESS, articleService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param article
     */
    @PostMapping()
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return new Result(true, StatusCode.OK, Message.SAVE_SUCCESS);
    }

    /**
     * 修改
     *
     * @param article
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Article article, @PathVariable String id) {
        article.setId(id);
        articleService.update(article);
        return new Result(true, StatusCode.OK, Message.UPDATE_SUCCESS);
    }

    /**
     * 删除
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        articleService.deleteById(id);
        return new Result(true, StatusCode.OK, Message.DELETE_SUCCESS);
    }

}
