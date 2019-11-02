package com.bridgelabz.demo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
	@Bean
	public ModelMapper modelmapper()
	{
		return new ModelMapper();
	}

}
