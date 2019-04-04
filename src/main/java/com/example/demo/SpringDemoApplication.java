package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.boot.SpringApplication.run;
/**
 * @Author: ChangYu
 * @Version 1.0
 */
@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@MapperScan("com.example.demo.dao")
@EnableTransactionManagement
public class SpringDemoApplication {

	public static void main(String[] args) {
		run(SpringDemoApplication.class, args);
	}



}

