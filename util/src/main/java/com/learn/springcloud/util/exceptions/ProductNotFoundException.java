package com.learn.springcloud.util.exceptions;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException() {}

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ProductNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
