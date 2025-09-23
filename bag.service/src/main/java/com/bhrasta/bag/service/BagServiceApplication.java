package com.bhrasta.bag.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BagServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BagServiceApplication.class, args);
	}

}
