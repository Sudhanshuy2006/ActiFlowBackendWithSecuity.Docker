package com.ActiFitFlowApp.service;

import com.ActiFitFlowApp.dto.RecommendationRequest;
import com.ActiFitFlowApp.model.Activity;
import com.ActiFitFlowApp.model.Recommendation;
import com.ActiFitFlowApp.model.User;
import com.ActiFitFlowApp.repository.ActivityRepository;
import com.ActiFitFlowApp.repository.RecommendationRepository;
import com.ActiFitFlowApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final RecommendationRepository recommendationRepository;

    public Recommendation generateRecommendation(RecommendationRequest request) {
     User user = userRepository.findById(request.getUserId())
             .orElseThrow(()-> new RuntimeException("Recommendation not found by User: " + request.getUserId()));

     Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(()-> new RuntimeException("Activity not found by User: " + request.getActivityId()));

     Recommendation recommendation = Recommendation.builder()
             .user(user)
             .activity(activity)
             .improvement(request.getImprovement())
             .safety(request.getSafety())
             .suggestions(request.getSuggestions())
             .build();

     return  recommendationRepository.save(recommendation);
    }

    public List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public List<Recommendation> getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId);
    }
}
