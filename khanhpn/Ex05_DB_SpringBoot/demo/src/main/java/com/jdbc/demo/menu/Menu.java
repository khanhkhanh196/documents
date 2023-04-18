package com.jdbc.demo.menu;

import com.jdbc.demo.commom.Printer;
import com.jdbc.demo.menu.jdbcSimple.JdbcSimpleMenu;
import com.jdbc.demo.menu.jdbcTemplate.JdbcTemplateMenu;
import com.jdbc.demo.menu.jpa.JpaMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    @Autowired
    JpaMenu jpaMenu;
    @Autowired
    JdbcTemplateMenu jdbcTemplateMenu;
    @Autowired
    JdbcSimpleMenu jdbcSimpleMenu;
    @Autowired
    Printer printer;

    public void showMenu(){
        int index = 0;
        printer.print(++index+".Connect to database with JPA");
        printer.print(++index+".Connect to database with JdbcTemplate");
        printer.print(++index+".Connect to database with JdbcSimple");
        printer.print(++index+".Exit");
        printer.print("Enter your choice");
    }

    public void showMenu2() {
        int index = 0;
        printer.print(++index+".Category menu");
        printer.print(++index+".Product menu");
        printer.print(++index+".Warehouse menu");
        printer.print(++index+".Back to main menu");
        printer.print("Enter your choice");
    }

    public void showMenuJPA() {
        boolean checker = true;
        while(checker) {
            try {
                showMenu2();
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                switch (choice){
                    case 1:
                        jpaMenu.showMenuCategoryJPA();
                        break;
                    case 2:
                        jpaMenu.showMenuProductJPA();
                        break;
                    case 3:
                        jpaMenu.showMenuWarehouseJPA();
                        break;
                    case 4:
                        checker = false;
                        menu();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                printer.printError(e.getMessage());
            }
        }

    }

    public void showMenuSimpleJdbc() {
        boolean checker = true;
        while(checker) {
            try {
                showMenu2();
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                switch (choice){
                    case 1:
                        jdbcSimpleMenu.showMenuCategory();
                        break;
                    case 2:
                        jdbcSimpleMenu.showMenuProduct();
                        break;
                    case 3:
                        jdbcSimpleMenu.showMenuWarehouse();
                        break;
                    case 4:
                        checker = false;
                        menu();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                printer.printError(e.getMessage());
            }
        }

    }

    public void showMenuJdbcTemplate(){
        try{
            int choice = 0;
            while(choice<4) {
                showMenu2();
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                switch (choice){
                    case 1:
                        jdbcTemplateMenu.showMenuCategory();
                        break;
                    case 2:
                        jdbcTemplateMenu.showMenuProduct();
                        break;
                    case 3:
                        jdbcTemplateMenu.showMenuWarehouse();
                        break;
                    case 4:
                        choice = 5;
                        menu();
                        break;
                    default:
                        printer.print("You enter is invalid");
                        break;
                }
            }

        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    public void menu(){
        showMenu();
        try {
            int choice = 0;
            while(choice<5) {
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                switch (choice){
                    case 1:
                        showMenuJPA();
                        break;
                    case 2:
                        showMenuJdbcTemplate();
                        break;
                    case 3:
                        showMenuSimpleJdbc();
                        break;
                    case 4:
                        System.exit(-1);
                        break;
                    default:
                        break;
                }
            }

        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }
}
