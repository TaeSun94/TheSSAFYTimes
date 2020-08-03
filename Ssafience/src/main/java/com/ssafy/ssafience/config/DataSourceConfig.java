package com.ssafy.ssafience.config;


import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
	
	@Bean
	public DataSource dataSource() {
		String username = "ssafy";
		String password = "ssafy2020";
		String jdbcUrl = "jdbc:mysql://maria.cpmvlqdxidqc.ap-northeast-2.rds.amazonaws.com:3306/ssafytimes";
		String driverClass = "com.mysql.cj.jdbc.Driver";
		
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password);
		dataSourceBuilder.url(jdbcUrl);
		dataSourceBuilder.driverClassName(driverClass);
		return dataSourceBuilder.build();
	}
}
