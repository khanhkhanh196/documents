package com.jdbc.demo.demo;


import com.jdbc.demo.daoJdbcSimple.CategoryImpl;
import com.jdbc.demo.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@SpringBootApplication(scanBasePackages = {"com.jdbc.demo"})
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages={"com.jdbc.demo"})
@EntityScan("com.jdbc.demo.modelJPA")
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private Menu menu;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menu.menu();
	}
}
