package com.sapo.publisher.service;

import com.sapo.publisher.model.OrderCreatedExchange;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Component
public class LoggingService {
    private Logger myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
    public void printOrderToFile(OrderCreatedExchange order) {
        myLogger.info(order.toString());

    }

}
