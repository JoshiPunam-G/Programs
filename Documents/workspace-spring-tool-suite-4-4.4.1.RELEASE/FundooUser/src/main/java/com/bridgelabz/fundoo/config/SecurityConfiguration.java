package com.bridgelabz.fundoo.config;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.modelmapper.ModelMapper;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import com.bridgelabz.fundoo.response.Response;
import springfox.documentation.builders.PathSelectors;
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
	private MongoDbFactory mongoDbFactory;
	  @Bean
	  
	 
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())    
	          
	          .build();                                           
	    }
	 
	  public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/user").allowedOrigins("http://localhost:8080");
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
	  
	  
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
		redisStandaloneConfiguration.setPassword(RedisPassword.of("redis@123"));
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
//
//	@Bean
//	public RedisTemplate redisTemplate() {
//		RedisTemplate template = new RedisTemplate<>();
//		template.setConnectionFactory(jedisConnectionFactory());
//		return template;
//	}
//	
	

}
