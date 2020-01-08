package com.tensquare.article.pojo;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author MuYang
 * @date 2020/1/8
 */
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = -5470386151661600672L;

    @Id
    private String _id;
    private String articleid;
    private String content;
    private String userid;
    private String parentid;
    private Date publishdate;
}
