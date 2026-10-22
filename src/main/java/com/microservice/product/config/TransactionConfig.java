package com.microservice.product.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class TransactionConfig {

    @Autowired
    private Environment environmentConfig;

    @Bean
    public DataSource dataSourceInstance() {
        DriverManagerDataSource dataSourceBean = new DriverManagerDataSource();
        dataSourceBean.setDriverClassName(environmentConfig.getProperty("jdbc.driver"));
        dataSourceBean.setUrl(environmentConfig.getProperty("jdbc.url"));
        dataSourceBean.setUsername(environmentConfig.getProperty("jdbc.username"));
        dataSourceBean.setPassword(environmentConfig.getProperty("jdbc.password"));
        return dataSourceBean;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSourceInstance) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSourceInstance);
        factoryBean.setPackagesToScan("com.microservice.product.entity");

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", environmentConfig.getProperty("hibernate.dialect"));
        hibernateProperties.put("hibernate.show_sql", environmentConfig.getProperty("hibernate.show_sql"));
        hibernateProperties.put("hibernate.hbm2ddl.auto", environmentConfig.getProperty("hibernate.hbm2ddl.auto"));

        factoryBean.setHibernateProperties(hibernateProperties);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory activeSessionFactory) {
        HibernateTransactionManager transactionHandler = new HibernateTransactionManager();
        transactionHandler.setSessionFactory(activeSessionFactory);
        return transactionHandler;
    }
}
