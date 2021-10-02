package com.learn.springcloud.productcompositeservice.adapter.in.web;

import com.learn.springcloud.util.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-composite")
public class ProductCompositeController {

    @GetMapping("/{id}")
    public String getProductCompositeById(@PathVariable int id) {
        throw new NotFoundException("Error aja");
    }
}
