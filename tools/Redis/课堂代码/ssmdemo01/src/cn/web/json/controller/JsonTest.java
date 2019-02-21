package cn.web.json.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.web.json.mapper.ItemsMapper;
import cn.web.json.pojo.Items;
import cn.web.json.service.ItemsService;


@Controller
public class JsonTest {
	
	@Autowired
	private ItemsService itemsService;
	
	//请求json串(商品信息)，输出json(商品信息)
	//@RequestBody将请求的商品信息的json串转成itemsCustom对象
	//@ResponseBody将itemsCustom转成json输出
	@RequestMapping("/requestJson")
	public @ResponseBody Items requestJson(@RequestBody Items itemsCustom){
		//@ResponseBody将itemsCustom转成json输出
		return itemsCustom;
	}
	
	//请求key/value，输出json
	@RequestMapping("/responseJson")
	@ResponseBody
	public Items responseJson(Items itemsCustom){
		//@ResponseBody将itemsCustom转成json输出
		return itemsCustom;
	}

	@RequestMapping("/addItemsSubmit")
	@ResponseBody
	public List<Items> addItemsSubmit(Items item){
		System.out.println(item);
		itemsService.addItems(item);
		List<Items> list = itemsService.findAllItems();
		return list;
	}

}
