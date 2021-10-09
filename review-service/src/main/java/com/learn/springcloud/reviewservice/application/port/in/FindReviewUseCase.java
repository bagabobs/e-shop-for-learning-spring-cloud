package com.learn.springcloud.reviewservice.application.port.in;

import com.learn.springcloud.core.review.domain.Review;
import com.learn.springcloud.util.exceptions.ReviewNotFoundException;

import java.util.List;

public interface FindReviewUseCase {
    List<Review> getReviewsByProductId(int productId) throws ReviewNotFoundException;
}
