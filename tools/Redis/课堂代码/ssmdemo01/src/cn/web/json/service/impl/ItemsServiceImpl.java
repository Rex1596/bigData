package cn.web.json.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.web.json.mapper.ItemsMapper;
import cn.web.json.pojo.Items;
import cn.web.json.pojo.ItemsExample;
import cn.web.json.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService {
	
	@Autowired
	private ItemsMapper itemsMapper;

	@Override
	public void addItems(Items item) {
		itemsMapper.insert(item);
	}

	@Override
	public List<Items> findAllItems() {
		ItemsExample example = new ItemsExample();
		
		return itemsMapper.selectByExample(example);
	}
	
	

}
