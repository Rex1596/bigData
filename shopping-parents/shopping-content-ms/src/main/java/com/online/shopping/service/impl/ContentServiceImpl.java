package com.online.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.online.shopping.entity.PageResult;
import com.online.shopping.mapper.TbContentMapper;
import com.online.shopping.pojo.TbContent;
import com.online.shopping.pojo.TbContentExample;
import com.online.shopping.pojo.TbContentExample.Criteria;
import com.online.shopping.service.ContentService;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper mapper;
	@Autowired
	private RedisTemplate redisTemplate;
	/**
	 * 查询全部
	 */
	@Override
	public List<TbContent> findAll() {
		return mapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbContent> page= (Page<TbContent>) mapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbContent tbContent) {
		mapper.insert(tbContent); // 插入广告
		redisTemplate.boundHashOps("content").delete(tbContent.getCategoryId());
	}

	/**
	 * 修改
	 */
	@Override
	public void update(TbContent content){
		// 修改广告信息
		mapper.updateByPrimaryKey(content);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbContent findOne(Long id){
		// 查询广告表的信息
		return mapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			TbContent content = mapper.selectByPrimaryKey(id);
			//删除缓存
			redisTemplate.boundHashOps("content").delete(content.getCategoryId());
			mapper.deleteByPrimaryKey(id);
		}
	}

	
	@Override
	public List<TbContent> findByCategory(Long categoryId) {
		//加入缓存代码
		List<TbContent> list = (List<TbContent>) redisTemplate.boundHashOps("content").get(categoryId);
		
		if (null == list) {
			System.out.println("查询数据库");
			TbContentExample example = new TbContentExample();
			Criteria criteria = example.createCriteria();
			
			criteria.andStatusEqualTo("1");
			criteria.andCategoryIdEqualTo(categoryId);
			example.setOrderByClause("sort_order");
			list = mapper.selectByExample(example);
			redisTemplate.boundHashOps("content").put(categoryId, list);
		} else {
			System.out.println("存缓存中获取");
		}
		
		return list;
	}
	
}
