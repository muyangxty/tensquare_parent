package com.tensquares.spit.service;

import com.tensquares.spit.pojo.Spit;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author MuYang
 * @date 2020/1/8
 */
public interface ISpitService {

    List<Spit> findAll();

    Spit findById(String spitId);

    void save(Spit spit);

    void update(Spit spit);

    void deleteById(String spitId);

    /**
     * 吐槽点赞
     */
    void addThumbup(String spitId);

    /**
     * 分页查询
     * @param spit
     * @param page
     * @param size
     * @return
     */
    Page<Spit> pageQuery(Spit spit, int page, int size);

    /**
     * 根据上级id,查询吐槽数据
     *
     * @param parentid 上级id
     * @param page     页码
     * @param size     每页记录数
     * @return
     */
    Page<Spit> QuerySpitByParentId(String parentid, Integer page, Integer size);
}
