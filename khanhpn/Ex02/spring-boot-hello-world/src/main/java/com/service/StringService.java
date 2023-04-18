package com.service;

import com.common.Printer;
import com.constant.Common;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StringService {

    private Printer printer;

    @Autowired
    private StringService(Printer printer) {
        this.printer = printer;
    }

    public void findContainAny() {
        try {
            String string = enterString();
            if(StringUtils.isEmpty(string.trim())){
                printer.print("Empty String");
                return;
            }
            String subString = enterSubString();
            StopWatch started = StopWatch.createStarted();
            if(StringUtils.containsAny(string,subString)){
                printer.timeToExecute("findContainAny",started.getTime());
                started.stop();
                printer.printIfMatch(string,subString);
            } else {
                printer.printIfNotMatch(string,subString);
                System.out.println(started.getTime());
            }
        } catch (Exception e) {
            printer.print(String.valueOf(e.getCause()));
            printer.print("Your enter is invalid");
        }
    }

    public void findContainsIgnoreCase() {
        try {
            String string = enterString();
            if(StringUtils.isEmpty(string.trim())){
                printer.print("Empty String");
                return;
            }
            String subString = enterSubString();
            StopWatch started = StopWatch.createStarted();
            if(StringUtils.containsIgnoreCase(string,subString)) {
                printer.timeToExecute("findContainsIgnoreCase",started.getTime());
                started.stop();
                printer.printIfMatch(string,subString);
            } else {
                printer.printIfNotMatch(string,subString);
            }
        } catch (Exception e) {
            printer.print(String.valueOf(e.getCause()));
            printer.print("Your enter is invalid");
        }
    }

    public void countMatchesFromAGivenString(){
        try {
            String string = enterString();
            if(StringUtils.isEmpty(string.trim())){
                printer.print("Empty String");
                return;
            }
            String subString = enterSubString();
            StopWatch started = StopWatch.createStarted();
            int matches = StringUtils.countMatches(string,subString);
            printer.timeToExecute("countMatchesFromAGivenString",started.getTime());
            started.stop();
            printer.print(subString+" appeared " + matches + "times in "+string);
        } catch (Exception e) {
            printer.print("Your enter is invalid");
        }
    }

    public void addSuffixIfMissing(){
        try {
            String string = enterString();
            if(StringUtils.isEmpty(string.trim())){
                printer.print("Empty String");
                return;
            }
            StopWatch started = StopWatch.createStarted();
            String stringWithSuffix = StringUtils.appendIfMissing(string, Common.SUFFIX);
            printer.timeToExecute("addSuffixIfMissing",started.getTime());
            started.stop();
        } catch (Exception e) {
            printer.print(String.valueOf(e.getCause()));
            printer.print("Your enter is invalid");
        }
    }

    public void addPrefixIfMissing() {
        try {
            String string = enterString();
            if(StringUtils.isEmpty(string.trim())){
                printer.print("Empty String");
                return;
            }
            StopWatch started = StopWatch.createStarted();
            String stringWithPrefix = StringUtils.prependIfMissing(string, Common.PREFIX);
            printer.timeToExecute("addPrefixIfMissing",started.getTime());
            started.stop();
        } catch (Exception e) {
            printer.print(String.valueOf(e.getCause()));
            printer.print("Your enter is invalid");
        }
    }
    public void toLowerCase(){
        try {
            String string = enterString();
            if(StringUtils.isEmpty(string.trim())){
                printer.print("Empty String");
                return;
            }
            StopWatch started = StopWatch.createStarted();
            StringUtils.lowerCase(string);
            printer.timeToExecute("toLowerCase",started.getTime());
            started.stop();
            printer.print(string);
        }catch (Exception e){
            printer.print(e.getMessage());
        }
    }

    public void toUpperCase(){
        try {
            String string = enterString();
            if(StringUtils.isEmpty(string.trim())){
                printer.print("Empty String");
                return;
            }
            StopWatch started = StopWatch.createStarted();
            StringUtils.upperCase(string);
            printer.timeToExecute("toUpperCase",started.getTime());
            started.stop();
            printer.print(string);
        }catch (Exception e){
            printer.print(e.getMessage());
        }
    }
    public String enterString() {
        Scanner sc = new Scanner(System.in);
        printer.print("Enter the String: ");
        String string = sc.nextLine();
        return string;
    }

    public String enterSubString(){
        Scanner sc = new Scanner(System.in);
        printer.print("Enter a String or a Character you want to check");
        String subString = sc.nextLine();
        return subString;
    }
}
