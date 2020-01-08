package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 问答相关的持久层接口
 *
 * @author Administrator
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

    @Query(value = "select * from tb_problem a, tb_pl b, tb_label c where a.id = b.problemid and b.labelid = ? order by replytime desc ", nativeQuery = true)
    public Page<Problem> newAnswer(String labelid, Pageable pageable);

    @Query(value = "select * from tb_problem a, tb_pl b, tb_label c where a.id = b.problemid and b.labelid = ? order by reply desc ", nativeQuery = true)
    public Page<Problem> hotAnswer(String labelid, Pageable pageable);

    @Query(value = "select * from tb_problem a, tb_pl b, tb_label c where a.id = b.problemid and b.labelid = ? and reply = 0 order by createtime desc ", nativeQuery = true)
    public Page<Problem> WaitAnswer(String labelid, Pageable pageable);


}
