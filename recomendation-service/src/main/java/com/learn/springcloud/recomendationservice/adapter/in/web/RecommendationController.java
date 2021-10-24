package com.learn.springcloud.recomendationservice.adapter.in.web;

import com.learn.springcloud.core.recommendation.domain.Recommendation;
import com.learn.springcloud.recomendationservice.application.port.in.FindRecommendationUseCase;
import com.learn.springcloud.util.exceptions.NotFoundException;
import com.learn.springcloud.util.exceptions.RecommendationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommendation")
public class RecommendationController {
    private final FindRecommendationUseCase findRecommendationUseCase;

    @GetMapping("/{productId}")
    public List<Recommendation> getProductRecommendation(@PathVariable int productId) {
        try {
            return findRecommendationUseCase.getRecommendationsByProductId(productId);
        } catch(RecommendationNotFoundException e) {
            throw new NotFoundException();
        }
    }
}
