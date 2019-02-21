package com.online.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ShoppingEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingEurekaServerApplication.class, args);
	}
}
