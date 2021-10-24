package com.learn.springcloud.core.review.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    int productId;
    int reviewId;
    String author;
    String subject;
    String content;
    String serviceAddress;
}
