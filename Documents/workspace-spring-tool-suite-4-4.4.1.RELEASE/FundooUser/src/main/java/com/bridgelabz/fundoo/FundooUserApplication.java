package com.bridgelabz.fundoo;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan({"com.bridgelabz.fundoo.controller.UserController" ,"com.bridgelabz.fundoo.repository"})
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,WebMvcAutoConfiguration.class})
@SpringBootApplication
public class FundooUserApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FundooUserApplication.class, args);
	}
}
