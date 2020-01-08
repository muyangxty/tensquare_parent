package com.tensquare.crecuit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.crecuit.pojo.Enterprise;

import java.util.List;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface EnterpriseDao extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {

    /**
     * 根据热门状态，获取企业列表
     *
     * @param ishot 热门状态码
     * @return
     */
    public List<Enterprise> findByIshot(String ishot);
}
