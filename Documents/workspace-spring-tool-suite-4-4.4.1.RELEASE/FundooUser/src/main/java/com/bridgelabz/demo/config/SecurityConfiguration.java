package com.bridgelabz.demo.config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	 
	  @Bean 
	  public PasswordEncoder passwordEncoder() 
	  { 
		  return new BCryptPasswordEncoder(); 
	  }
	 
	 @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	      .csrf().disable()
	      .authorizeRequests()
	      .antMatchers("HttpMethod.POST,/register").permitAll()
	      .antMatchers("HttpMethod.POST,/login")
	      .authenticated()
	      .and().httpBasic()
	      .and().sessionManagement().disable();
	  }
	 
	  @Bean
	  public Response response()
	  {
		  return new Response();
	  }
	 
}
