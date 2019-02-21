package com.online.shopping.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbContentCategory;
import com.online.shopping.service.ContentCategoryService;

/**
 * controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/contentCategory")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService service;

	/**
	 * 返回全部列表
	 * 
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbContentCategory> findAll() {
		return service.findAll();
	}

	/**
	 * 返回全部列表
	 * 
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows) {
		return service.findPage(page, rows);
	}

	/**
	 * 增加
	 * 
	 * @param goods
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbContentCategory tbContentCategory) {
		try {
			service.add(tbContentCategory);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}

	/**
	 * 修改
	 * 
	 * @param goods
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbContentCategory contentCategory) {
		try {
			service.update(contentCategory);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}

	/**
	 * 获取实体
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbContentCategory findOne(Long id) {
		return service.findOne(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long[] ids) {
		try {
			service.delete(ids);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}

	/*	*//**
			 * 查询+分页
			 * 
			 * @param brand
			 * @param page
			 * @param rows
			 * @return
			 *//*
				 * @RequestMapping("/search") public PageResult search(@RequestBody TbGoods
				 * goods, int page, int rows ){
				 * 
				 * //String sellerId =
				 * SecurityContextHolder.getContext().getAuthentication().getName(); String
				 * sellerId = "yqtech"; //暂时设定
				 * 
				 * goods.setSellerId(sellerId);
				 * 
				 * return service.findPage(goods, page, rows); }
				 */

}
