package com.learn.springcloud.core.recommendation;

import lombok.Value;

@Value
public class Recommendation {
    int productId;
    int recommendationId;
    String author;
    int rate;
    String content;
    String serviceAddress;
}
