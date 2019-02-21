package cn.web.json.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.web.json.pojo.Items;
import cn.web.json.service.ItemsService;

@Controller
public class AjaxController {
	
	@Autowired
	private ItemsService itemsService;

	//1. @RequestBody 表示把客户端的json字符串转成javabean, 不常用
	//2. @ResponseBody 表示把javabean转换成json数据返回到客户端   必须用   
	@RequestMapping("/queryItem1")
	@ResponseBody
	public Items queryItem1(@RequestBody Items items) {
		return items;
		
	}
	
	@RequestMapping("/queryItem2")
	@ResponseBody
	public Items queryItem2(Items items,String realName) {
		System.out.println(items.toString());
		System.out.println(realName);
		return items;
		
	}
	
	@RequestMapping("/addItems")
	@ResponseBody
	public List<Items> addItems(Items item){		
		itemsService.addItems(item);
		List<Items> list = itemsService.findAllItems();
		return list;
		
	}
}
