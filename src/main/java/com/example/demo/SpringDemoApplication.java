package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.boot.SpringApplication.run;
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpringDemoApplication {

	public static void main(String[] args) {
		run(SpringDemoApplication.class, args);
	}

}

