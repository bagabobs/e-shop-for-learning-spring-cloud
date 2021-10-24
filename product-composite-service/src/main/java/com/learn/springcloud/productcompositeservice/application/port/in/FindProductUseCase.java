package com.learn.springcloud.productcompositeservice.application.port.in;

import com.learn.springcloud.core.composite.domain.ProductAggregate;
import com.learn.springcloud.core.product.domain.Product;
import com.learn.springcloud.core.recommendation.domain.Recommendation;
import com.learn.springcloud.core.review.domain.Review;
import com.learn.springcloud.util.exceptions.ProductAggregateNotFoundException;
import com.learn.springcloud.util.exceptions.ProductNotFoundException;
import com.learn.springcloud.util.exceptions.RecommendationNotFoundException;
import com.learn.springcloud.util.exceptions.ReviewNotFoundException;

import java.util.List;

public interface FindProductUseCase {
    ProductAggregate getProductAggregate(int id) throws ProductAggregateNotFoundException;
    List<Recommendation> getRecomendationsProduct(int id) throws RecommendationNotFoundException;
    List<Review> getReviewsProduct(int id) throws ReviewNotFoundException;
    Product getProduct(int id) throws ProductNotFoundException;
}
