package com.learn.springcloud.productservice.application.port.in;

import com.learn.springcloud.core.product.domain.Product;
import com.learn.springcloud.util.exceptions.ProductNotFoundException;

public interface FindProductUseCase {
    Product getProductById(int id) throws ProductNotFoundException;
}
