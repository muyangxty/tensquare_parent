package com.tensquare.article.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 实体类
 *
 * @author MuYang
 * @date 2020/1/8
 */
@Data
@Entity
@Table(name = "tb_channel")
public class Channel implements Serializable {

    private static final long serialVersionUID = 6517059660292031145L;

    @Id
    private String id;//ID
    //频道名称
    private String name;
    //状态
    private String state;

}
