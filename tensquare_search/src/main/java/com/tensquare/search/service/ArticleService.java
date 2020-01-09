package com.tensquare.search.service;

import com.tensquare.search.Mapper.ArticleMapper;
import com.tensquare.search.pojo.Article;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * @author MuYang
 * @date 2020/1/9
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加文章
     *
     * @param article
     */
    public void save(Article article) {
        article.setId(idWorker.nextId() + "");
        articleMapper.save(article);
    }

    /**
     * 分页搜索
     *
     * @param key  搜索字段
     * @param page 页码
     * @param size 每页记录数
     * @return
     */
    public Page<Article> findBykey(String key, Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        return this.articleMapper.findByTitleOrContentLike(key, key, pageable);
    }
}
