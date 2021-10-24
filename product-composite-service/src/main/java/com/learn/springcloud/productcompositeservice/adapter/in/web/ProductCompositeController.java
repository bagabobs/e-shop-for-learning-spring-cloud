package com.learn.springcloud.productcompositeservice.adapter.in.web;

import com.learn.springcloud.core.composite.domain.ProductAggregate;
import com.learn.springcloud.productcompositeservice.application.port.in.FindProductUseCase;
import com.learn.springcloud.util.exceptions.NotFoundException;
import com.learn.springcloud.util.exceptions.ProductAggregateNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-composite")
@Slf4j
@RequiredArgsConstructor
public class ProductCompositeController {
    private final FindProductUseCase findProductUseCase;

    @GetMapping("/{id}")
    public ProductAggregate getProductAggregate(@PathVariable int id) {
        try {
            return findProductUseCase.getProductAggregate(id);
        } catch(ProductAggregateNotFoundException e) {
            throw new NotFoundException("Error aja");
        }
    }
}
