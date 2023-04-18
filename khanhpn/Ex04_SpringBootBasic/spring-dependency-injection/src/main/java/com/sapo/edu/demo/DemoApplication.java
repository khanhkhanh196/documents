package com.sapo.edu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    @Autowired
    Menu menu;

    @Autowired
    PrinterFile printerFile;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }
    public void run(String... args) throws Exception {

        printerFile.initialLogger();

        Customer customer1 = new Customer("ABC", "1234", new BigDecimal(5000000));
        Customer customer2 = null;

        boolean check = true;
        while(check) {
            int choice = 0;

            System.out.println("1. Create a second Customer");
            System.out.println("2. Print current ATM Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Deposit Money");
            System.out.println("5. Print Customer Infomation: ");
            System.out.println("6. Exit.");
            System.out.println("Enter choice");
            try {
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
            switch (choice) {
                case 1 :
                    customer2 = menu.createNewCustomer();
                    break;
                case 2:
                    menu.printCurrentMoney();
                    break;
                case 3:
                    menu.withDraw(customer2);
                    break;
                case 4:
                    menu.deposit(customer2);
                    break;
                case 5:
                    menu.printCustomerToFile(customer2);
                    break;
                case 6:
                    check = false;
                    break;
                default:
                    System.out.println("YOUR ENTER IS INVALID - PLEASE ENTER AGAIN");
                    break;
            }


        }

    }
}
