package com.tensquare.qa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author MuYang
 * @date 2020/1/7
 */
@Entity
@Data
@Table(name = "tb_pl")
public class Pl implements Serializable {
    private static final long serialVersionUID = -5649514653928913134L;


    @Id
    private String problemid;

    @Id
    private String lableid;
}
