package com.learn.springcloud.productservice.adapter.in.web;

import com.learn.springcloud.core.product.domain.Product;
import com.learn.springcloud.productservice.application.port.in.FindProductUseCase;
import com.learn.springcloud.util.exceptions.NotFoundException;
import com.learn.springcloud.util.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final FindProductUseCase findProductUseCase;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        try {
            return findProductUseCase.getProductById(id);
        } catch(ProductNotFoundException e) {
            throw new NotFoundException("Product Not Found");
        }
    }
}
