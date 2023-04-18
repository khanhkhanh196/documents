package com.jdbc.demo.commom;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;
@Component
public class PrinterImpl implements Printer{
    private final static Logger myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Override
    public void print(String message) {
        myLogger.info(message);
    }

    @Override
    public void printError(String message) {
        myLogger.warning(message);
    }
}
