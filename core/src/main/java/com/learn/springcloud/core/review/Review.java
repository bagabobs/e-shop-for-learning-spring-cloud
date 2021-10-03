package com.learn.springcloud.core.review;

import lombok.Value;

@Value
public class Review {
    int productId;
    int reviewId;
    String author;
    String subject;
    String content;
    String serviceAddress;
}
