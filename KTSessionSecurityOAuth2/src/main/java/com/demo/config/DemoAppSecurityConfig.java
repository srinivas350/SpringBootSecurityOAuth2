package com.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoAppSecurityConfig {
	
	public SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception{
		
		httpSec.authorizeRequests()
				.antMatchers("/login/**","/oauth2/**","/oauth2**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.oauth2Login();
		
		return httpSec.build();
	}

}
