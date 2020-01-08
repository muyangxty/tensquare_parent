package com.tensquares.spit.service.impl;

import com.tensquares.spit.mapper.SpitMapper;
import com.tensquares.spit.pojo.Spit;
import com.tensquares.spit.service.ISpitService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @author MuYang
 * @date 2020/1/8
 */
@Service
@Transactional
public class SpitServiceImpl implements ISpitService {

    @Autowired
    private SpitMapper spitMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 查询所有吐槽
     *
     * @return
     */
    public List<Spit> findAll() {
        return this.spitMapper.findAll();
    }

    /**
     * 根据id,查询吐槽
     *
     * @param spitId id
     * @return
     */
    public Spit findById(String spitId) {
        return this.spitMapper.findById(spitId).get();
    }

    /**
     * 新增吐槽
     *
     * @param spit 吐槽数据
     */
    public void save(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishtime(new Date());
        spit.setVisits(0);
        spit.setThumbup(0);
        spit.setShare(0);
        spit.setComment(0);
        spit.setState("1");
        if (StringUtils.isEmpty(spit.getParentid())){
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        this.spitMapper.save(spit);
    }

    /**
     * 修改吐槽
     *
     * @param spit
     */
    public void update(Spit spit) {
        this.spitMapper.save(spit);
    }

    /**
     * 根据id,删除吐槽
     *
     * @param spitId id
     */
    public void deleteById(String spitId) {
        this.spitMapper.deleteById(spitId);
    }

    /**
     * 吐槽点赞
     *
     * @param spitId
     */
    @Override
    public void addThumbup(String spitId) {
//        Spit spit = this.spitMapper.findById(spitId).get();
//        spit.setThumbup((spit.getThumbup() == null ? 0 :spit.getThumbup()) + 1);
//        this.spitMapper.save(spit);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is("1"));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }

    /**
     * 分页查询
     *
     * @param spit
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Spit> pageQuery(Spit spit, int page, int size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        return this.spitMapper.findByPageable(pageable);
    }

    /**
     * 根据上级id,查询吐槽数据
     *
     * @param parentid 上级id
     * @param page     页码
     * @param size     每页记录数
     * @return
     */
    @Override
    public Page<Spit> QuerySpitByParentId(String parentid, Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        return this.spitMapper.findByParentid(parentid, pageable);
    }
}
