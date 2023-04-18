package com.restapi.restapidemo.controlleradvice;

import com.restapi.restapidemo.exception.WarehouseException.WarehouseErrorResponse;
import com.restapi.restapidemo.exception.WarehouseException.WarehouseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WarehouseRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<WarehouseErrorResponse> handleException(WarehouseNotFoundException exc) {
        WarehouseErrorResponse error = new WarehouseErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<WarehouseErrorResponse> handleException(Exception exc) {
        WarehouseErrorResponse error = new WarehouseErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
