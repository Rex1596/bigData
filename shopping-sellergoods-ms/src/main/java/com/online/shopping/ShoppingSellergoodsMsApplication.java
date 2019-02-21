package com.online.shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages="com.online.shopping.mapper")
public class ShoppingSellergoodsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingSellergoodsMsApplication.class, args);
	}
}
