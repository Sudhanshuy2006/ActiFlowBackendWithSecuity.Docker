package com.ActiFitFlowApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationRequest {
    private String userId;
    private String activityId;
    private List<String> improvement;
    private List<String> suggestions;
    private List<String> safety;
}
