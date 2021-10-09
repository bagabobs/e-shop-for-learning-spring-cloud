package com.learn.springcloud.util.exceptions;

public class ProductAggregateNotFoundException extends Exception {
    public ProductAggregateNotFoundException() {}

    public ProductAggregateNotFoundException(String message) {
        super(message);
    }

    public ProductAggregateNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ProductAggregateNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
