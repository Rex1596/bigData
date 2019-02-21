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
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" }) // 创建spring容器环境
public class TestRedisClient {

	@Autowired
	private ItemsService itemService;
	
	@Autowired
	private JedisPool jedisPool;

	@Test // 测试：数据库的数据导入到redis中
	public void testImportData() {
		// 先创建redis的可访问的客户端
		//Jedis jedis = new Jedis("localhost", 6379);
		Jedis jedis = jedisPool.getResource();
		List<Items> list = itemService.findAllItems();
		for (Items items : list) {
			// itemList_ids: [1,2,3,4,5,6]
			jedis.lpush("itemList_ids", items.getId() + "");
			jedis.hset("item"+items.getId(), "id", items.getId()+"");
			jedis.hset("item"+items.getId(), "name", items.getName());
			jedis.hset("item"+items.getId(), "price", items.getPrice()+"");
			jedis.hset("item"+items.getId(), "createtime", items.getCreatetime()+"");
			jedis.hset("item"+items.getId(), "detail", items.getDetail());
		}
	}
	
	@Test
	public void testQueryRedis() {
		//Jedis jedis = new Jedis("localhost", 6379);
		Jedis jedis = jedisPool.getResource();
		//先遍历存放 id号的数组
		List<String> idList = jedis.lrange("itemList_ids", 0, -1);
		for (String id : idList) {
			Map<String, String> itemMap = jedis.hgetAll("item"+id);
			//遍历map
			Set<Map.Entry<String, String>> set = itemMap.entrySet();
			for (Map.Entry<String, String> entry : set) {
				System.out.println(entry.getKey() + "::" + entry.getValue());
			}
		}
	}
	
	
}
