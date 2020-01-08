package com.tensquares.spit.mapper;

import com.tensquares.spit.pojo.Spit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 吐槽相关的持久层接口
 *
 * @author MuYang
 * @date 2020/1/8
 */
public interface SpitMapper extends MongoRepository<Spit, String> {

    public Page<Spit> findByParentid(String parentid, Pageable pageable);

    public Page<Spit> findByPageable(Pageable pageable);


}
