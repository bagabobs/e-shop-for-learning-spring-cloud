package com.learn.springcloud.recomendationservice.application.services;

import com.learn.springcloud.core.recommendation.domain.Recommendation;
import com.learn.springcloud.recomendationservice.application.port.in.FindRecommendationUseCase;
import com.learn.springcloud.util.exceptions.RecommendationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService implements FindRecommendationUseCase {
    @Override
    public List<Recommendation> getRecommendationsByProductId(int id) throws RecommendationNotFoundException {
        try {
            List<Recommendation> recommendationList = new ArrayList<>();
            recommendationList.add(new Recommendation(id, 1, "Author", 1, "Content", ""));
            return recommendationList;
        } catch(Exception e) {
            throw new RecommendationNotFoundException("Error: not found recommendation", e);
        }
    }
}
