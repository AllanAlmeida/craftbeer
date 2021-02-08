package com.beerhouse.config;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.distribution.Version.v5_6_21;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wix.mysql.config.MysqldConfig;

@Configuration
public class DataBaseConfig {

  private static MysqldConfig config;
  
  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Value("${spring.datasource.username}")
  private String username;
  
  @Value("${spring.datasource.password}")
  private String password;
  
  
  @Bean
  @SuppressWarnings("rawtypes")
  public DataSource dataSource() {
	  
	  embeddMySQL();
	  
	  DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	  
      dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
      dataSourceBuilder.url(dbUrl);
      dataSourceBuilder.username(username);
      dataSourceBuilder.password(password);
      
      return dataSourceBuilder.build();
  }
  
	public void embeddMySQL() {
		
		config = MysqldConfig.aMysqldConfig(v5_6_21)
		        .withPort(3306)
		        .withUser(username, password)
		        .build();
		
		anEmbeddedMysql(config).start();
	}
}
	