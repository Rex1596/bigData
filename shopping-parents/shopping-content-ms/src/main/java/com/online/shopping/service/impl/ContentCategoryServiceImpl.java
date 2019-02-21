package com.online.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.online.shopping.entity.PageResult;
import com.online.shopping.mapper.TbContentCategoryMapper;
import com.online.shopping.pojo.TbContentCategory;
import com.online.shopping.service.ContentCategoryService;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper mapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbContentCategory> findAll() {
		return mapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbContentCategory> page= (Page<TbContentCategory>) mapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbContentCategory tbContentCategory) {
		mapper.insert(tbContentCategory); // 插入广告
	}

	/**
	 * 修改
	 */
	@Override
	public void update(TbContentCategory contentCategory){
		// 修改广告信息
		mapper.updateByPrimaryKey(contentCategory);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbContentCategory findOne(Long id){
		// 查询广告表的信息
		return mapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			mapper.deleteByPrimaryKey(id);
		}
	}
	
}
