package com.learn.springcloud.core.composite.domain;

import lombok.Value;

@Value
public class RecommendationSummary {
    int recommendationId;
    String author;
    int rate;
}
