package com.tensquare.base.service.impl;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.mapper.LabelMapper;
import com.tensquare.base.service.ILabelService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签业务层实现类
 *
 * @author MuYang
 * @date 2020/1/6
 */
@Service
@Transactional
public class LabelServiceImpl implements ILabelService {

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 查询所有标签
     *
     * @return
     */
    @Override
    public List<Label> findAll() {
        return labelMapper.findAll();
    }

    /**
     * 根据id,查询标签
     *
     * @param labelId 标签id
     * @return
     */
    @Override
    public Label findById(String labelId) {
        //从redis缓存中查询当前对象
        Label label = (Label)redisTemplate.opsForValue().get("Label_" + labelId);
        //校验查询结果是否为null
        if (label == null){
            //从查询数据库
            label = this.labelMapper.findById(labelId).get();
            //存入redis缓存中
            redisTemplate.opsForValue().set("Label_" + labelId, label);
        }
        return label;
    }

    /**
     * 新增标签
     *
     * @param label 标签数据
     */
    @Override
    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        this.labelMapper.save(label);
    }

    /**
     * 修改标签
     *
     * @param labelId 标签id
     * @return
     */
    @Override
    public void updateById(String labelId, Label label) {
        //删除redis中未修改的缓存
        redisTemplate.delete("label_" + labelId);
        this.labelMapper.save(label);
    }

    /**
     * 删除标签
     *
     * @param labelId 标签id
     * @return
     */
    @Override
    public void deleteById(String labelId) {
        //删除redis中的缓存
        redisTemplate.delete("label_" + labelId);
        this.labelMapper.deleteById(labelId);
    }

    /**
     * 搜索标签
     *
     * @param label
     * @return
     */
    @Override
    public List<Label> findSearch(Label label) {
        return this.labelMapper.findAll(createSpecification(label));
    }

    /**
     * 分页查询
     *
     * @param label
     * @param page  页码
     * @param size  每页记录数
     * @return
     */
    @Override
    public Page<Label> pageQuery(Label label, Integer page, Integer size) {
        //封装分页对象---(持久层框架springdata jpa)
        PageRequest pageable = PageRequest.of(page - 1, size);
        return this.labelMapper.findAll(createSpecification(label), pageable);
    }

    /**
     * 构建查询条件
     *
     * @param label
     * @return
     */
    private Specification<Label> createSpecification(Label label) {
        return new Specification<Label>() {
            /**
             * 构建查询条件
             * @param root 根对象，就是要把条件封装到那个对象中，where 类名=label.getid
             * @param query 封装的都是查询的关键字，比如
             * @param criteriaBuilder 用来封装条件对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //初始化一个list集合，用来存放所有条件
                List<Predicate> list = new ArrayList<>();
                //校验labelname
                if (StringUtils.isNotBlank(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                //校验状态
                if (StringUtils.isNotBlank(label.getState())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                //校验recommend
                if (StringUtils.isNotBlank(label.getRecommend())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("recommend").as(String.class), label.getRecommend());
                    list.add(predicate);
                }
                //初始化一个数组，作为最终返回值的条件
                Predicate[] parr = new Predicate[list.size()];
                //将list集合转成数组
                list.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        };
    }

}
