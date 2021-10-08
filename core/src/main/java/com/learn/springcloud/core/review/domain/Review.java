package com.learn.springcloud.core.review.domain;

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
