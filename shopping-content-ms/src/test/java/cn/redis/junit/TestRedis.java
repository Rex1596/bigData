package cn.redis.junit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.redis.App;
import cn.redis.pojo.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {App.class})
public class TestRedis {

	@Autowired
	private RedisTemplate
	redisTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void testSetString() {
		this.stringRedisTemplate.opsForValue().set("msg", "msg信息");
		System.out.println("保存完毕");
	}
	
	@Test
	public void testGetString() {
		String msg = this.stringRedisTemplate.opsForValue().get("msg");
		System.out.println(msg);
	}
	
	@Test
	public void testSetObject() {
		this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		Users user = new Users(1, "mike", 20);
		this.redisTemplate.opsForValue().set("user", user);
		System.out.println("保存完毕");
	}
	
	@Test
	public void testGetObject() {
		this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		Users user = (Users) this.redisTemplate.opsForValue().get("user");
		System.out.println(user.getId()+","+user.getName()+","+user.getAge());
	}
	
	@Test
	public void testSetHash() {
		List<String> list = new ArrayList<>();
		list.add("orange");
		list.add("apple");
		
		redisTemplate.boundHashOps("fruits").put(1, list);
		System.out.println("保存完毕");
	}
	
	@Test
	public void testGetHash() {

		List<String> list = (List<String>) redisTemplate.boundHashOps("fruits").get(1);
		for (String name : list) {
			System.out.println(name);
		}
	}
	
}
