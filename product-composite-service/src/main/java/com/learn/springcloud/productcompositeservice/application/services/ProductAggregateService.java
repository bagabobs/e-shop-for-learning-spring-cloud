package com.learn.springcloud.productcompositeservice.application.services;

import com.learn.springcloud.core.composite.domain.ProductAggregate;
import com.learn.springcloud.core.composite.domain.RecommendationSummary;
import com.learn.springcloud.core.composite.domain.ReviewSummary;
import com.learn.springcloud.core.composite.domain.ServiceAddresses;
import com.learn.springcloud.core.product.domain.Product;
import com.learn.springcloud.core.recommendation.domain.Recommendation;
import com.learn.springcloud.core.review.domain.Review;
import com.learn.springcloud.productcompositeservice.application.port.in.FindProductUseCase;
import com.learn.springcloud.util.ServiceUtil;
import com.learn.springcloud.util.exceptions.ProductAggregateNotFoundException;
import com.learn.springcloud.util.exceptions.ProductNotFoundException;
import com.learn.springcloud.util.exceptions.RecommendationNotFoundException;
import com.learn.springcloud.util.exceptions.ReviewNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import static org.springframework.http.HttpMethod.GET;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ProductAggregateService implements FindProductUseCase {
    private final RestTemplate restTemplate;
    private final ServiceUtil serviceUtil;

    private final String productUrl;
    private final String reviewUrl;
    private final String recommendationUrl;

    private Product product;
    private List<Review> reviewList;
    private List<Recommendation> recommendationList;
    private ProductAggregate productAggregate;
    private List<ReviewSummary> reviewSummaryList;
    private List<RecommendationSummary> recommendationSummaryList;

    @Autowired
    public ProductAggregateService(
            RestTemplate restTemplate,
            ServiceUtil serviceUtil,
            @Value("${app.product-service.host}") String productHost,
            @Value("${app.product-service.port}") String productPort,
            @Value("${app.review-service.host}") String reviewHost,
            @Value("${app.review-service.port}") String reviewPort,
            @Value("${app.recommendation-service.host}") String recommendationHost,
            @Value("${app.recommendation-service.port}") String recommendationPort
            ) {
        this.restTemplate = restTemplate;
        this.serviceUtil = serviceUtil;

        this.productUrl = "http://" + productHost + ":" + productPort;
        log.info(this.productUrl);
        this.reviewUrl = "http://" + reviewHost + ":" + reviewPort;
        log.info(this.reviewUrl);
        this.recommendationUrl = "http://" + recommendationHost + ":" + recommendationPort;
        log.info(this.recommendationUrl);
    }

    @Override
    public ProductAggregate getProductAggregate(int id) throws ProductAggregateNotFoundException {
        try {
            product = getProduct(id);
            reviewList = getReviewsProduct(id);
            recommendationList = getRecomendationsProduct(id);

             createProductAggregate();
            return productAggregate;
        } catch(Exception e) {
            log.info("Error: get product aggragate: " + e.getMessage());
            throw new ProductAggregateNotFoundException();
        }
    }

    private void createProductAggregate() {
        mapReviewListToReviewSummaryList();
        mapRecommendationListToRecommendationList();
        productAggregate = new ProductAggregate(product.getProductId(), product.getName(), product.getWeight(),
                recommendationSummaryList, reviewSummaryList, new ServiceAddresses("", "", "", ""));
    }

    private void mapReviewListToReviewSummaryList() {
        reviewSummaryList = new ArrayList<>();
        reviewList.forEach(review -> {
            reviewSummaryList.add(new ReviewSummary(review.getReviewId(), review.getAuthor(), review.getSubject()));
        });
    }

    private void mapRecommendationListToRecommendationList() {
        recommendationSummaryList = new ArrayList<>();
        recommendationList.forEach(recommendation -> {
            recommendationSummaryList.add(new RecommendationSummary(recommendation.getRecommendationId(), recommendation.getAuthor(), recommendation.getRate()));
        });
    }

    @Override
    public List<Recommendation> getRecomendationsProduct(int id) throws RecommendationNotFoundException {
        try {
            String recommendationsProductUrl = recommendationUrl + "/recommendation/" + id;
            return restTemplate.exchange(recommendationsProductUrl, GET, null, new ParameterizedTypeReference<List<Recommendation>>() {
            }).getBody();
        } catch(Exception e) {
            throw new RecommendationNotFoundException("Error: not found recommendation for product", e);
        }
    }

    @Override
    public List<Review> getReviewsProduct(int id) throws ReviewNotFoundException {
        try {
            String reviewsProductUrl = reviewUrl + "/review/" + id;
            return restTemplate.exchange(reviewsProductUrl, GET, null, new ParameterizedTypeReference<List<Review>>() {
            }).getBody();
        } catch(Exception e) {
            throw new ReviewNotFoundException("Error: not found review for product", e);
        }
    }

    @Override
    public Product getProduct(int id) throws ProductNotFoundException {
        try {
            String productIdUrl = productUrl + "/product/" + id;
            return restTemplate.getForObject(productIdUrl, Product.class);
        } catch(Exception e) {
            throw new ProductNotFoundException("Error: not found product", e);
        }
    }


}
