package com.restapi.restapidemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.logging.Logger;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class RestApiDemoApplication implements CommandLineRunner{
	private static final Logger myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static void main(String[] args) {
		SpringApplication.run(RestApiDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
