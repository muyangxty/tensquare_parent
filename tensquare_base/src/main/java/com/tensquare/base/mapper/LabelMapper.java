package com.tensquare.base.mapper;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 标签数据访问接口
 *
 * @author MuYang
 * @date 2020/1/6
 */
public interface LabelMapper extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {
}
