package com.string.lang;

import com.common.Printer;
import com.service.StringService;
import com.service.TimeService;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    public static final Scanner SCANNER = new Scanner(System.in);
    @Autowired
    private Printer printer;
    @Autowired
    private StringService service;
    @Autowired
    private TimeService timeService;
    public void show() {
        showMenu();
        int choice;
        boolean check = true;
        while (check) {
            showMenu();
            choice = NumberUtils.toInt(SCANNER.nextLine());
            switch (choice) {
                case 1:
                    service.findContainAny();
                    break;
                case 2:
                    service.findContainsIgnoreCase();
                    break;
                case 3:
                    service.countMatchesFromAGivenString();
                    break;
                case 4:
                    service.addPrefixIfMissing();
                    break;
                case 5:
                    service.addSuffixIfMissing();
                    break;
                case 6:
                    service.toUpperCase();
                    break;
                case 7:
                    service.toLowerCase();
                    break;
                case 8:
                    check = false;
                    break;
                case 9:
                    timeService.calenderUltils();
                    break;
                default:
                    printer.print("Choose !!!!");
                    break;
            }
        } ;
    }

    public void showMenu(){
        int index = 0;
        printer.print("----------------------------------");
        printer.print(++index +". Contains any or String");
        printer.print(++index +". Contains ignore case");
        printer.print(++index +". Count matches");
        printer.print(++index +". Add Prefix if missing");
        printer.print(++index +". Add Suffix if missing");
        printer.print(++index +". Upper case");
        printer.print(++index +". Lower case");
        printer.print(++index +". Exit");
        printer.print("Enter your choice?");
    }
}
