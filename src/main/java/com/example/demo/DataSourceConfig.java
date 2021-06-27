package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${demo.datasource.driver-class-name}")
    private String dbDriverClass;

    @Value("${demo.datasource.url}")
    private String url;

    @Value("${demo.datasource.username}")
    private String uname;

    @Value("${demo.datasource.password}")
    private String pwd;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(dbDriverClass);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(uname);
        dataSourceBuilder.password(pwd);
        return dataSourceBuilder.build();
    }

}
