package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Message;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author MuYang
 * @date 2020/1/8
 */
@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 评论文章
     *
     * @param comment
     * @return
     */
    @PostMapping()
    public Result save(@RequestBody Comment comment) {
        this.commentService.save(comment);
        return new Result(true, StatusCode.OK, Message.SAVE_SUCCESS);
    }
}
