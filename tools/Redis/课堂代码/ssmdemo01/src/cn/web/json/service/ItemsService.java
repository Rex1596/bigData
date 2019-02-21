package cn.web.json.service;

import java.util.List;

import cn.web.json.pojo.Items;

public interface ItemsService {

	public void addItems(Items item);
	
	public List<Items> findAllItems();
}
