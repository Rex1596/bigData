package com.shopping.content;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(value="com.shopping.content.mapper")
public class AppContentMs {

	public static void main(String[] args) {
		SpringApplication.run(AppContentMs.class, args);
	}
	
}
