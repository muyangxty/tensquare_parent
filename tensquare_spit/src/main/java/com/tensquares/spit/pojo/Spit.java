package com.tensquares.spit.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MuYang
 * @date 2020/1/8
 */
@Data
public class Spit implements Serializable {

    private static final long serialVersionUID = -8010546578523746400L;

    @Id
    private String _id;

    private String content;
    private Date publishtime;
    private String userid;
    private String nickname;
    private Integer visits;
    private Integer thumbup;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentid;

}
