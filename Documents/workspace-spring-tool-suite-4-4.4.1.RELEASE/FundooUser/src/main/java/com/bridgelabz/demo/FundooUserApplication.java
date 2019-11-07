package com.bridgelabz.demo;



import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;



//@EnableMongoRepositories("com.bridgelabz.demo.repository")

@SpringBootApplication
public class FundooUserApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FundooUserApplication.class, args);
	}
}
