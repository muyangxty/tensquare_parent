package com.tensquare.article.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 *
 * @author MuYang
 * @date 2020/1/8
 */
@Entity
@Data
@Table(name = "tb_column")
public class Column implements Serializable {

    private static final long serialVersionUID = -2550444687706559787L;

    @Id
    private String id;//ID
    //专栏名称
    private String name;
    //专栏简介
    private String summary;
    //用户ID
    private String userid;
    //申请日期
    private Date createtime;
    //审核日期
    private Date checktime;
    //状态
    private String state;

}
