package com.online.shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(value="com.online.shopping.mapper")
public class ShoppingContentMs {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingContentMs.class, args);
	}
}
