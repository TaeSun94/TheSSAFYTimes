package com.ssafy.ssafience.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.ssafience.security.JwtAuthenticationFilter;
import com.ssafy.ssafience.util.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${jwt.secret}")
	private String secret;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		Filter filter = new JwtAuthenticationFilter(authenticationManager(), jwtUtil());
		http
		.cors().disable()
		.csrf().disable()
		.formLogin().disable()
		.headers().frameOptions().disable()
		.and()
		.addFilter(filter)
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil(secret);
	}

}
