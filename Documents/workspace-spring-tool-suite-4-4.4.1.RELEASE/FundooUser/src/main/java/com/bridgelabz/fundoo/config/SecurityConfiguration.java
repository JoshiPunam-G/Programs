package com.bridgelabz.fundoo.config;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bridgelabz.fundoo.response.Response;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableConfigurationProperties
@EnableSwagger2
public class SecurityConfiguration {

	
	  @Bean 
	  public  static PasswordEncoder passwordEncoder() 
	  { 
		  return new BCryptPasswordEncoder(); 
	  }
	  @Bean
	    public Docket productApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select().apis(RequestHandlerSelectors.basePackage("com.bridgelabz.demo"))
	                .build();
	    }
	 
	  @Bean
	  public ModelMapper model()
	  {
		return new ModelMapper();
		  
	  }
	 
	  
	  @Bean
	  public Response response()
	  {
		  return new Response(0, null);
	  }

}
