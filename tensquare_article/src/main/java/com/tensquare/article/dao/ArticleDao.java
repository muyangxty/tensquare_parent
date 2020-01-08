package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 文章线相关的持久层接口
 *
 * @author MuYang
 * @date 2020/1/8
 */
public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

    /**
     * 修改审核状态
     * @param id
     */
    @Modifying
    @Query(value = "update tb_article set state = 1 where id = ?", nativeQuery = true)
    public void updateState(String id);

    /**
     * 修改点赞数
     * @param id
     */
    @Query(value = "update tb_article set thumbup = thumbup + 1 where id = ?", nativeQuery = true)
    public void addThumbup(String id);


}
