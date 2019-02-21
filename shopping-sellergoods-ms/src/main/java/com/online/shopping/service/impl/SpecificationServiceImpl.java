package com.online.shopping.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.online.shopping.entity.PageResult;
import com.online.shopping.mapper.TbSpecificationMapper;
import com.online.shopping.mapper.TbSpecificationOptionMapper;
import com.online.shopping.pojo.TbSpecification;
import com.online.shopping.pojo.TbSpecificationExample;
import com.online.shopping.pojo.TbSpecificationExample.Criteria;
import com.online.shopping.pojo.TbSpecificationOption;
import com.online.shopping.pojo.TbSpecificationOptionExample;
import com.online.shopping.pojogroup.Specification;
import com.online.shopping.service.SpecificationService;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}
	
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper ;
	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		// 保存规格
		specificationMapper.insert(specification.getSpecification());
		// 保存规格选项
		for(TbSpecificationOption specificationOption: specification.getSpecificationOptionList()){
			// 设置规格的ID:
			specificationOption.setSpecId(specification.getSpecification().getId());
			
			specificationOptionMapper.insert(specificationOption);
		}
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
		// 修改规格
		specificationMapper.updateByPrimaryKey(specification.getSpecification());
		
		// 先删除规格选项，再添加规格选项
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
		criteria.andSpecIdEqualTo(specification.getSpecification().getId());
		specificationOptionMapper.deleteByExample(example);
		
		// 保存规格选项
		for(TbSpecificationOption specificationOption: specification.getSpecificationOptionList()){
			// 设置规格的ID:
			specificationOption.setSpecId(specification.getSpecification().getId());
			
			specificationOptionMapper.insert(specificationOption);
		}
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		Specification specification = new Specification();
		// 根据规格ID查询规格对象
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		specification.setSpecification(tbSpecification);
		
		// 根据规格的ID查询规格选项
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
		criteria.andSpecIdEqualTo(id);
		List<TbSpecificationOption> list = specificationOptionMapper.selectByExample(example);
		specification.setSpecificationOptionList(list);
		
		return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			// 删除规格
			specificationMapper.deleteByPrimaryKey(id);
			
			// 删除规格选项:
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo(id);
			specificationOptionMapper.deleteByExample(example);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

		@Override
		public List<Map> selectOptionList() {
			return specificationMapper.selectOptionList();
		}
	
}
