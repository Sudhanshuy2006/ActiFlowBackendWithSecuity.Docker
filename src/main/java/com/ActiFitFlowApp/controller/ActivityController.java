package com.ActiFitFlowApp.controller;

import com.ActiFitFlowApp.dto.ActivityRequest;
import com.ActiFitFlowApp.dto.ActivityResponse;
import com.ActiFitFlowApp.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {
     private final ActivityService activityService;
    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request){
        System.out.println("🔥 ACTIVITY API HIT");
        return ResponseEntity.ok(activityService.trackActivity(request));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader(value = "X-User-ID") String userId){
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }

}
