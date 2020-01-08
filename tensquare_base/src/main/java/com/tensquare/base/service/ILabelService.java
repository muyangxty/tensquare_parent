package com.tensquare.base.service;

import com.tensquare.base.pojo.Label;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 标签业务层接口
 *
 * @author MuYang
 * @date 2020/1/6
 */
public interface ILabelService {

    /**
     * 根据id,查询标签
     *
     * @param labelId 标签id
     * @return
     */
    Label findById(String labelId);

    /**
     * 查询所有标签
     *
     * @return
     */
    List<Label> findAll();

    /**
     * 新增标签
     *
     * @param label 标签数据
     */
    void save(Label label);

    /**
     * 修改标签
     *
     * @param labelId 标签id
     * @return
     */
    void updateById(String labelId, Label label);

    /**
     * 删除标签
     *
     * @param labelId 标签id
     * @return
     */
    void deleteById(String labelId);

    /**
     * 搜索标签
     *
     * @param label
     * @return
     */
    List<Label> findSearch(Label label);

    /**
     * 分页查询
     * @param label
     * @param page 页码
     * @param size 每页记录数
     * @return
     */
    Page<Label> pageQuery(Label label, Integer page, Integer size);
}
