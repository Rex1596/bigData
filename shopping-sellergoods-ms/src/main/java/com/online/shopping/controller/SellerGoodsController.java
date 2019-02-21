package com.online.shopping.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbBrand;
import com.online.shopping.service.BrandService;

@RestController
@RequestMapping("/brand-Ms")
public class SellerGoodsController {
	
	@Autowired
	private BrandService brandService;

	@RequestMapping("/findAll")
	public List<TbBrand> findAllBrands() {
		return brandService.findAll();
	}
	
	@RequestMapping("/findPage")
	public PageResult findPage(int pageNum,int pageSize) {
		return brandService.findPage(pageNum, pageSize);		
	}
	
	@RequestMapping("/add")
	public Result add(@RequestBody TbBrand brand) {
		try {
			brandService.add(brand);
			return new Result(true, "成功添加品牌信息");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "添加品牌信息失败");
		}
	}
	
	@RequestMapping("/findBrandDetail")
	public TbBrand findBrandDetail(Long id) {
		return brandService.findOne(id);
	}
	
	@RequestMapping("/update")
	public Result updateBrand(@RequestBody TbBrand brand) {
		try {
			brandService.update(brand);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}		
	}
	
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			brandService.delete(ids);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}	
	}
	
	@RequestMapping("/selectOptionList")
	public List<Map> selectOptionList() {
		return brandService.selectOptionList();
	}
	
}
