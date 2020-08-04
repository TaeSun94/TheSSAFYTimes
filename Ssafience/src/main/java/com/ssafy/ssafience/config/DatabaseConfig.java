package com.ssafy.ssafience.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
		basePackages = "com.ssafy.ssafience.repo"
)
public class DatabaseConfig {}