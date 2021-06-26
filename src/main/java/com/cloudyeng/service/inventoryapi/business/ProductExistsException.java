package com.cloudyeng.service.inventoryapi.business;

public class ProductExistsException extends Exception {

    public ProductExistsException() {
        super();
    }

    public ProductExistsException(String message) {
        super(message);
    }

    public ProductExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductExistsException(Throwable cause) {
        super(cause);
    }

    protected ProductExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
