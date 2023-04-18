package com.restapi.restapidemo.exception.WarehouseException;

public class WarehouseNotFoundException extends RuntimeException {
    public WarehouseNotFoundException() {
        super();
    }

    public WarehouseNotFoundException(String message) {
        super(message);
    }

    public WarehouseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WarehouseNotFoundException(Throwable cause) {
        super(cause);
    }

    protected WarehouseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
