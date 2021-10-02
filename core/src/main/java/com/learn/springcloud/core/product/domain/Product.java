package com.learn.springcloud.core.product.domain;

import lombok.Value;

@Value
public class Product {
    int productId;
    String name;
    int weight;
    String serviceAddress;
}

