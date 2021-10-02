package com.learn.springcloud.util.exceptions;

public class ServiceAddressNotFoundException extends Exception {

    public ServiceAddressNotFoundException() {}

    public ServiceAddressNotFoundException(String message) {
        super(message);
    }

    public ServiceAddressNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ServiceAddressNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
