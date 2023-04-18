package com.sapo.edu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Logger;

@Component
public class Menu {
    @Autowired
    Atm bidvAtm;
    private final static Logger myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Customer createNewCustomer() {
        Customer customer = null;
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter account number : ");
            String accNo = sc.nextLine();
            System.out.println("Enter pin: ");
            String pin = sc.nextLine();
            System.out.println("Enter balance:  ");
            BigDecimal balance = sc.nextBigDecimal();
            customer = new  Customer(accNo,pin,balance);
        } catch (Exception e) {
            myLogger.info("You enter is invalid, please try again");
        }

        return customer;
    }
    public void printCurrentMoney(){
        bidvAtm.printCurrentMoney();
    }

    public void printCustomerToFile(Customer customer) {
        bidvAtm.printCustomerToFile(customer);
    }

    public void withDraw(Customer customer){
        if(customer != null) {
            try {
                Scanner sc = new Scanner(System.in);
                BigDecimal amount = sc.nextBigDecimal();
                bidvAtm.withDraw(customer, amount);
            } catch (Exception e) {
                myLogger.info(e.getMessage());
            }

        } else {
            myLogger.info("The Customer Not Found");
        }

    }

    public void deposit(Customer customer){
        if(customer != null) {
            try {
                Scanner sc = new Scanner(System.in);
                BigDecimal amount = sc.nextBigDecimal();
                bidvAtm.deposit(customer, amount);
            } catch (Exception e) {
                myLogger.info(e.getMessage());
            }
        } else {
            myLogger.info("The Customer Not Found");
        }

    }
}
