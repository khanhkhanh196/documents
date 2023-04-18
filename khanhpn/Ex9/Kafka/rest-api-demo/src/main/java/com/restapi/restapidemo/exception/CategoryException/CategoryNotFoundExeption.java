package com.restapi.restapidemo.exception.CategoryException;

public class CategoryNotFoundExeption extends RuntimeException {
    public CategoryNotFoundExeption() {
        super();
    }

    public CategoryNotFoundExeption(String message) {
        super(message);
    }

    public CategoryNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFoundExeption(Throwable cause) {
        super(cause);
    }

    protected CategoryNotFoundExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
