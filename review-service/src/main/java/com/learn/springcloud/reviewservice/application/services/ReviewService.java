package com.learn.springcloud.reviewservice.application.services;

import com.learn.springcloud.core.review.domain.Review;
import com.learn.springcloud.reviewservice.application.port.in.FindReviewUseCase;
import com.learn.springcloud.util.exceptions.ReviewNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService implements FindReviewUseCase {
    @Override
    public List<Review> getReviewsByProductId(int productId) throws ReviewNotFoundException {
        try {
            List<Review> reviewList = new ArrayList<>();
            reviewList.add(new Review(productId, 1, "One", "subject", "content", "service addr"));
            return reviewList;
        } catch(Exception e) {
            throw new ReviewNotFoundException("Error: ");
        }
    }
}
