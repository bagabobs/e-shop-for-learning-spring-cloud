package com.learn.springcloud.util.exceptions;

public class ReviewNotFoundException extends Exception {

    public ReviewNotFoundException() {}

    public ReviewNotFoundException(String message) {
        super(message);
    }

    public ReviewNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ReviewNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
