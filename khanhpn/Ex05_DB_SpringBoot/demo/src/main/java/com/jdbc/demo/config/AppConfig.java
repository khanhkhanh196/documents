package com.jdbc.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

//@Configuration
//@PropertySource("classpath:application.properties")
//public class AppConfig {
//    @Autowired
//    Environment env;
//
//    @Bean
//    public DataSource dataSource() {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        try {
//            dataSource.setDriverClass(env.getProperty("jdbc.driver"));
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
//
//
//        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
//        dataSource.setUser(env.getProperty("jdbc.user"));
//        dataSource.setPassword(env.getProperty("jdbc.password"));
//
//        dataSource.setInitialPoolSize(
//                getIntProperty("connection.pool.initialPoolSize"));
//
//        dataSource.setMinPoolSize(
//                getIntProperty("connection.pool.minPoolSize"));
//
//        dataSource.setMaxPoolSize(
//                getIntProperty("connection.pool.maxPoolSize"));
//
//        dataSource.setMaxIdleTime(
//                getIntProperty("connection.pool.maxIdleTime"));
//
//        return dataSource;
//    }
//
//    private int getIntProperty(String propName) {
//
//        String propVal = env.getProperty(propName);
//
//        // now convert to int
//        int intPropVal = Integer.parseInt(propVal);
//
//        return intPropVal;
//    }
//}
