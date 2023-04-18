package com.restapi.restapidemo.controlleradvice;

import com.restapi.restapidemo.exception.CategoryException.CategoryErrorResponse;
import com.restapi.restapidemo.exception.CategoryException.CategoryNotFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<CategoryErrorResponse> handleException(CategoryNotFoundExeption exc) {
        CategoryErrorResponse error = new CategoryErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    // Add another exception handler ... to catch any exception (catch all)

    @ExceptionHandler
    public ResponseEntity<CategoryErrorResponse> handleException(Exception exc) {

        CategoryErrorResponse error = new CategoryErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
