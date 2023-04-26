package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	/*
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity
			.authorizeHttpRequests( (requests) ->requests
				.requestMatchers("/","/homepage").permitAll()
				.anyRequest().authenticated())
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll());
		return httpSecurity.build();	
	}
	*/
	
	
	/*@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity
			httpSecurity) throws Exception{

		httpSecurity .authorizeHttpRequests( (requests) ->requests
				.requestMatchers("/homepage","homepage").permitAll()
				.requestMatchers("/department","department").permitAll()
				.requestMatchers("/searchdepartment","searchdepartment").permitAll()
				.requestMatchers("/searchemployee","searchemployee").permitAll()
				.requestMatchers("/employee","employee").permitAll()
				.anyRequest().authenticated()); return httpSecurity.build(); 
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().anyRequest().authenticated()
		.and().httpBasic();
		return httpSecurity.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
	}
	*/
}
