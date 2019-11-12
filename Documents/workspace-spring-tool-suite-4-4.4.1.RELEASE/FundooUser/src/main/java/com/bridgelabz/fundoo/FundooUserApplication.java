package com.bridgelabz.fundoo;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.bridgelabz.fundoo"})
@EnableMongoRepositories({"com.bridgelabz.fundoo.notes.repository","com.bridgelabz.fundoo.repository","com.bridgelabz.notes.controller" ,"com.bridgelabz.fundoo.label.repository"})
@EnableAutoConfiguration
@ComponentScan(basePackages= {"com.bridgelabz.fundoo.repository" ,"com.bridgelabz.fundoo.label.repository", "com.bridgelabz.fundoo.notes.repository" , "com.bridgelabz.notes.controller"})
public class FundooUserApplication {
	public static void main(String[] args) {
		SpringApplication.run(FundooUserApplication.class, args);
	}
}
