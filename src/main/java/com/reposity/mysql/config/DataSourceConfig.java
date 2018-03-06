package com.reposity.mysql.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name="apitest")
    @Qualifier("apitest")
    @ConfigurationProperties(prefix = "spring.datasource.apitest")
    public DataSource apitestDataSource(){
        return DataSourceBuilder.create().build();
    }

//    @Primary
    @Bean(name="demo")
    @Qualifier("demo")
    @ConfigurationProperties(prefix = "spring.datasource.demo")
    public DataSource demoDataSource(){
        return DataSourceBuilder.create().build();
    }


}
