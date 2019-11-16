package com.bridgelabz.fundoo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.mail.Multipart;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.net.MediaType;

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
