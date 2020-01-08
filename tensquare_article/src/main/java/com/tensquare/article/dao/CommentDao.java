package com.tensquare.article.dao;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author MuYang
 * @date 2020/1/8
 */
public interface CommentDao extends MongoRepository<Comment, String> {
}
