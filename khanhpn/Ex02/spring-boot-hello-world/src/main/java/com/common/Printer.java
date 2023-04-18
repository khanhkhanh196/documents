package com.common;

import org.springframework.stereotype.Component;

@Component
public class Printer {
    public void printIfMatch(String string, String subString){
        System.out.println(string +" did contain " + subString);
    }
    public void printIfNotMatch(String string , String subString){
        System.out.println(string +" didn't contain " + subString);
    }
    public void print(String message) {
        System.out.println(message);
    }
    public void timeToExecute(String methodName, long mili) {
        System.out.println(methodName+ " took " + mili +" miliseconds to complete" );
    }
}
