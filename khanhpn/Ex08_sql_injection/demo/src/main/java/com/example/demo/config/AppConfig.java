package com.example.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan("com.example.demo")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Autowired
    private Environment env;

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


    public int convertToInt(String prop) {
        int i = Integer.parseInt(env.getProperty(prop));
        return i;
    }
}
