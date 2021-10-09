package com.learn.springcloud.productcompositeservice.application.port.in;

import com.learn.springcloud.core.composite.domain.ProductAggregate;
import com.learn.springcloud.util.exceptions.ProductAggregateNotFoundException;

public interface FindProductUseCase {
    ProductAggregate getProductAggregate(int id) throws ProductAggregateNotFoundException;
}
