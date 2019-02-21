package com.online.shopping.service;

import java.util.List;

import com.online.shopping.entity.PageResult;
import com.online.shopping.pojo.TbContent;


/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface ContentService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbContent> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbContent tbContent);
	
	
	/**
	 * 修改
	 */
	public void update(TbContent tbContent);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbContent findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long [] ids);

	/**
	 * 根据ID获取图片list
	 * @param categoryId
	 * @return
	 */
	public	List<TbContent> findByCategory(Long categoryId);
}
