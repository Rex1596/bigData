package com.online.shopping.service;

import java.util.List;

import com.online.shopping.entity.PageResult;
import com.online.shopping.pojo.TbContentCategory;


/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface ContentCategoryService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbContentCategory> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbContentCategory tbContentCategory);
	
	
	/**
	 * 修改
	 */
	public void update(TbContentCategory tbContentCategory);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbContentCategory findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long [] ids);

}
