package com.learn.springcloud.util.exceptions;

public class RecommendationNotFoundException extends Exception {
    public RecommendationNotFoundException() {}

    public RecommendationNotFoundException(String message) {
        super(message);
    }

    public RecommendationNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public RecommendationNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
