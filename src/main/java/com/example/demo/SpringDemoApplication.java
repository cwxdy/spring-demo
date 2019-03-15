package com.example.demo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import static org.springframework.boot.SpringApplication.run;
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpringDemoApplication {

	public static void main(String[] args) {
		run(SpringDemoApplication.class, args);
	}

}

