package com.tensquare.search.Mapper;

import com.tensquare.search.pojo.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author MuYang
 * @date 2020/1/9
 */
public interface ArticleMapper extends ElasticsearchRepository<Article, String> {

    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
