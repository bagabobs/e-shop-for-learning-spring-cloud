package com.learn.springcloud.core.composite.domain;

import lombok.Value;

@Value
public class ReviewSummary {
    int reviewId;
    String author;
    String subject;
}
