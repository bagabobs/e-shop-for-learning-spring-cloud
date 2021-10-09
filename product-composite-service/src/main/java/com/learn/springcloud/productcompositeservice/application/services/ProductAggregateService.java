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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import static org.springframework.http.HttpMethod.GET;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductAggregateService implements FindProductUseCase {
    private final RestTemplate restTemplate;
    private final ServiceUtil serviceUtil;

    @Value("app.product-service.host")
    private final String productHost;
    @Value("app.product-service.port")
    private final int productPort;
    @Value("app.review-service.host")
    private final String reviewHost;
    @Value("app.review-service.port")
    private final int reviewPort;
    @Value("app.recommendation-service.host")
    private final String recommendationHost;
    @Value("app.recommendation-service.port")
    private final int recommendationPort;

    private final String productUrl = "http://" + productHost + ":" + productPort;
    private final String reviewUrl = "http://" + reviewHost + ":" + reviewPort;
    private final String recommendationUrl = "http://" + recommendationHost + ":" + recommendationPort;

    private Product product;
    private List<Review> reviewList;
    private List<Recommendation> recommendationList;
    private ProductAggregate productAggregate;
    private List<ReviewSummary> reviewSummaryList;
    private List<RecommendationSummary> recommendationSummaryList;

    @Override
    public ProductAggregate getProductAggregate(int id) throws ProductAggregateNotFoundException {
        try {
            product = getProduct(id);
            reviewList = getReviewsProduct(id);
            recommendationList = getRecomendationsProduct(id);

             createProductAggregate();
            return productAggregate;
        } catch(Exception e) {
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

    private List<Recommendation> getRecomendationsProduct(int id) throws RecommendationNotFoundException {
        try {
            String recommendationsProductUrl = recommendationUrl + "/" + id;
            return restTemplate.exchange(recommendationsProductUrl, GET, null, new ParameterizedTypeReference<List<Recommendation>>() {
            }).getBody();
        } catch(Exception e) {
            throw new RecommendationNotFoundException("Error: not found recommendation for product", e);
        }
    }

    private List<Review> getReviewsProduct(int id) throws ReviewNotFoundException {
        try {
            String reviewsProductUrl = reviewUrl + "/" + id;
            return restTemplate.exchange(reviewsProductUrl, GET, null, new ParameterizedTypeReference<List<Review>>() {
            }).getBody();
        } catch(Exception e) {
            throw new ReviewNotFoundException("Error: not found review for product", e);
        }
    }

    private Product getProduct(int id) throws ProductNotFoundException {
        try {
            String productIdUrl = productUrl + "/" + id;
            return restTemplate.getForObject(productIdUrl, Product.class);
        } catch(Exception e) {
            throw new ProductNotFoundException("Error: not found product", e);
        }
    }


}
