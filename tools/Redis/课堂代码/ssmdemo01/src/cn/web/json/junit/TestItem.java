package cn.web.json.junit;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.web.json.pojo.Items;
import cn.web.json.service.ItemsService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml"})
public class TestItem {

	@Autowired
	private ItemsService itemsService;
	
	@Autowired
	private JedisPool jedisPool;
	

	    在 Redis中的数据结构

	    list  itemList_ids [1,2,3,4]

		hash   key
		       itemList1    
			            id     1
						name   电脑
						price  5000
						createtime 2019-10-10
			   itemList2
			            id     2
						name   电脑2
						price  7000
						createtime 2019-10-10
	
	@Test
	public void testImportDataToRedis() {
		//获取redis的连接
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		//Jedis jedis = jedisPool.getResource();
		List<Items> list = itemsService.findAllItems();
		for (Items items : list) {
			//构建存储商品id的列表(List)
			jedis.lpush("itemList_ids", items.getId()+"");
			//商品的明细数据存储到HashSet
			jedis.hset("itemList"+items.getId(), "id", items.getId()+"");
			jedis.hset("itemList"+items.getId(), "name", items.getName());
			jedis.hset("itemList"+items.getId(), "price", items.getPrice()+"");
			jedis.hset("itemList"+items.getId(), "pic", items.getPic());
			jedis.hset("itemList"+items.getId(), "createtime", items.getCreatetime()+"");
			jedis.hset("itemList"+items.getId(), "detail", items.getDetail()+"");
		}
	
	}
	
	@Test
	public void testFindItemsFromRedis() {
		//Jedis jedis = new Jedis("127.0.0.1", 6379);
		//JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
		Jedis jedis = jedisPool.getResource();
		
		List<String> ids = jedis.lrange("itemList_ids", 0, -1);
		for (String id : ids) {
			Map<String, String> maps = jedis.hgetAll("itemList"+id);
			//maps.keySet();
			//maps.values();
			Set<Map.Entry<String, String>> sets = maps.entrySet();
			for (Map.Entry<String, String> itemEntry : sets) {
				System.out.println(itemEntry.getKey() + "::" + itemEntry.getValue());
			}
			System.out.println("----------------------------------------");
		}
	}
}
