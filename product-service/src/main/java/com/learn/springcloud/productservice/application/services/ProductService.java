package com.learn.springcloud.productservice.application.services;

import com.learn.springcloud.core.product.domain.Product;
import com.learn.springcloud.productservice.application.port.in.FindProductUseCase;
import com.learn.springcloud.util.ServiceUtil;
import com.learn.springcloud.util.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService implements FindProductUseCase {
    private final ServiceUtil serviceUtil;

    @Override
    public Product getProductById(int id) throws ProductNotFoundException {
        try {
            return new Product(id, "name-" + id, 123, serviceUtil.getServiceAddress());
        } catch(Exception e) {
            throw new ProductNotFoundException("Product: " + id + " not found", e);
        }
    }
}
