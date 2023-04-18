package com.restapi.restapidemo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.restapi.restapidemo")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        dataSource.setInitialPoolSize(convertToInt("connection.pool.initialPoolSize"));
        dataSource.setMinPoolSize(convertToInt("connection.pool.minPoolSize"));
        dataSource.setMaxPoolSize(convertToInt("connection.pool.maxPoolSize"));
        dataSource.setMaxIdleTime(convertToInt("connection.pool.maxIdleTime"));

        return dataSource;
    }

    public Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
        prop.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
        return prop;
    }
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        sessionFactoryBean.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));

        return sessionFactoryBean;
    }
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return  transactionManager;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    public int convertToInt(String prop) {
        int i = Integer.parseInt(env.getProperty(prop));
        return i;
    }
}
