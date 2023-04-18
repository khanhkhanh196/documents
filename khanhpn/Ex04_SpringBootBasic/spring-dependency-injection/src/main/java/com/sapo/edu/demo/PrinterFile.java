package com.sapo.edu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.function.Supplier;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Component
public class PrinterFile implements Printer{

    private final static Logger myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void initialLogger() {
        FileHandler fileHandler;
        {
            try {
                fileHandler = new FileHandler("logging.log", true);
                myLogger.addHandler(fileHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void printCustomer(Customer customer) {
      myLogger.info(customer.toString());

    }

    @Override
    public void printMessage(String message) {
        myLogger.info(message);

    }
}
