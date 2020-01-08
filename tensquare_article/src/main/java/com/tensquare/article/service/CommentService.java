package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * @author MuYang
 * @date 2020/1/8
 */
@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 评论文章
     *
     * @param comment
     * @return
     */
    public void save(Comment comment) {
        comment.set_id(idWorker.nextId() + "");
        commentDao.save(comment);
    }

}
