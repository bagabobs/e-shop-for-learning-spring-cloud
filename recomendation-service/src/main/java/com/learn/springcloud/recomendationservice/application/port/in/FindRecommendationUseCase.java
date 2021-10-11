package com.learn.springcloud.recomendationservice.application.port.in;

import com.learn.springcloud.core.recommendation.domain.Recommendation;
import com.learn.springcloud.util.exceptions.RecommendationNotFoundException;

import java.util.List;

public interface FindRecommendationUseCase {
    List<Recommendation> getRecommendationsByProductId(int id) throws RecommendationNotFoundException;
}
