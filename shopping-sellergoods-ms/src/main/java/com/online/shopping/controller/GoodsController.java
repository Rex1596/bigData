package com.online.shopping.controller;

import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.online.shopping.activemq.JmsConfig;
import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbGoods;
import com.online.shopping.pojo.TbItem;
import com.online.shopping.pojogroup.Goods;
import com.online.shopping.service.GoodsService;



/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/goods-ms")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbGoods> findAll(){			
		return goodsService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return goodsService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param goods
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody Goods goods){
		System.out.println("#######"+goods.getGoods().getGoodsName()+"#######");
		System.out.println("#######"+goods.getGoodsDesc().getItemImages());
		System.out.println(goods.getGoods().getId());
		System.out.println("#######"+goods.getGoodsDesc().getSpecificationItems());
		System.out.println("#######"+goods.getGoodsDesc().getCustomAttributeItems());
		
		try {
			// 获得商家信息:
			//String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
			String sellerId = "yqtech"; //暂时设定
			goods.getGoods().setSellerId(sellerId);
			
			goodsService.add(goods);
			System.out.print("增加成功");
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param goods
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Goods goods){
		// 获得商家信息:
		//String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		String sellerId = "yqtech"; //暂时设定
		Goods goods2 = goodsService.findOne(goods.getGoods().getId());
		if(!sellerId.equals(goods2.getGoods().getSellerId()) || !sellerId.equals(goods.getGoods().getSellerId())){
			return new Result(false, "非法操作");
		}
		
		try {
			goodsService.update(goods);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public Goods findOne(Long id){
		return goodsService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			goodsService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbGoods goods, int page, int rows  ){
		
		//String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		String sellerId = "yqtech"; //暂时设定
		
		goods.setSellerId(sellerId);
		
		return goodsService.findPage(goods, page, rows);		
	}
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@RequestMapping("/updateStatus")
	public Result updateStatus(Long[] ids,String status){
		System.out.println("ids: " + ids);
		System.out.println("status: " + status);
		try {
			goodsService.updateStatus(ids, status);
			
			if("1".equals(status)){//如果是审核通过 
				//*****导入到索引库
				//得到需要导入的SKU列表
				List<TbItem> itemList = goodsService.findItemListByGoodsIdListAndStatus(ids, status);
				//导入到solr
				//itemSearchService.importList(itemList);	
				final String jsonString = JSON.toJSONString(itemList);//转换为json传输
				System.out.println("Goods conroller: " + jsonString);
				Topic topicSolrDestination = new ActiveMQTopic(JmsConfig.TOPIC_SOLR);
				jmsTemplate.send(topicSolrDestination, new MessageCreator() {
					
					@Override
					public Message createMessage(Session session) throws JMSException {
						
						return session.createTextMessage(jsonString);
					}
				});
				
				//****生成商品详细页
				for(final Long goodsId:ids){
					Topic topicPageDestination = new ActiveMQTopic(JmsConfig.TOPIC_HTML);
					jmsTemplate.send(topicPageDestination, new MessageCreator() {
						
						@Override
						public Message createMessage(Session session) throws JMSException {							
							return session.createTextMessage(goodsId+"");
						}
					});					
				}
				
			}		
			
			return new Result(true, "修改状态成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改状态失败");
		}
	}
	
}
