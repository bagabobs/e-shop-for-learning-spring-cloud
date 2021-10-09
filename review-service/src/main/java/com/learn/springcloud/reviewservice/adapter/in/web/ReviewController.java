package com.learn.springcloud.reviewservice.adapter.in.web;

import com.learn.springcloud.core.review.domain.Review;
import com.learn.springcloud.reviewservice.application.port.in.FindReviewUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/review")
public class ReviewController {
    private final FindReviewUseCase findReviewUseCase;

    @GetMapping("/{id}")
    public List<Review> getReviewListByProductId(@PathVariable int id) {

    }
}
