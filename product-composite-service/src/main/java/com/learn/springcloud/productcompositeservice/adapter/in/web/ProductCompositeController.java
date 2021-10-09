package com.learn.springcloud.productcompositeservice.adapter.in.web;

import com.learn.springcloud.util.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/product-composite")
@Slf4j
public class ProductCompositeController {
    private final RestTemplate restTemplate;

    private final String productUrl;
    private final String recommendationUrl;
    private final String reviewUrl;

    @Autowired
    public ProductCompositeController(
            RestTemplate restTemplate,
            @Value("app.product-service.port") int productPort,
            @Value("app.product-servie.host") String productHost,
            @Value("app.recommendation-service.port") int recommendationPort,
            @Value("app.recommendation-service.host") String recommendationHost,
            @Value("app.review-service.port") int reviewPort,
            @Value("app.review-service.host") String reviewHost
            ) {
        this.restTemplate = restTemplate;

        productUrl = "http://" + productHost + ":" + productPort;
        recommendationUrl = "http://" + recommendationHost + ":" + recommendationPort;
        reviewUrl = "http://" + reviewHost + ":" + reviewPort;
    }

    @GetMapping("/{id}")
    public String getProductCompositeById(@PathVariable int id) {
        throw new NotFoundException("Error aja");
    }
}
