package com.reposity.mysql.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryApitest",
        transactionManagerRef="transactionManagerApitest",
        basePackages= { "com.reposity.mysql.apitest" })//最后一个配置项设置实体类和Dao所在位置
public class ApiTestConifg {

    @Autowired
    @Qualifier("apitest")
    private DataSource apitestDataSource;

    @Primary
    @Bean(name="entityManagerApitest")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        return entityManagerFactoryApitest(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryApitest")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryApitest(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(apitestDataSource)
                .properties(getVendorProperties(apitestDataSource))
                .packages("com.reposity.mysql.apitest") //设置实体类所在位置
                .persistenceUnit("apitestPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Primary
    @Bean(name = "transactionManagerApitest")
    public PlatformTransactionManager transactionManagerApitest(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryApitest(builder).getObject());
    }

}
