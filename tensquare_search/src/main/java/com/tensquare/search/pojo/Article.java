package com.tensquare.search.pojo;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * 文章分词实体类
 *
 * @author MuYang
 * @date 2020/1/9
 */
@Document(indexName = "tensquare_article", type = "article")
@Data
public class Article implements Serializable {

    private static final long serialVersionUID = 3938554294220243169L;

    @Id
    private String id;

    //是否索引，就看该域能否被搜索
    //是否分词，就表示搜索的时候整体匹配还是单词匹配
    //是否存储，就是是否在页面上显示
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;

    private String state;

}
