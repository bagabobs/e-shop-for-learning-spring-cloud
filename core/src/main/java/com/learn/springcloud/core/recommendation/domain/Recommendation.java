package com.learn.springcloud.core.recommendation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Recommendation {
    int productId;
    int recommendationId;
    String author;
    int rate;
    String content;
    String serviceAddress;
}
