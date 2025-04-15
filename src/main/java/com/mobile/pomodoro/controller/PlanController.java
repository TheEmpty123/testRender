package com.mobile.pomodoro.controller;

import com.mobile.pomodoro.dto.response.PlanResponseDTO.PlanResponseDTO;
import com.mobile.pomodoro.entities.User;
import com.mobile.pomodoro.services.IPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class PlanController {
    @Autowired
    private IPlanService planService;

    // Add your plan-related endpoints here
    @GetMapping("recent-plan")
    @ResponseBody
    public PlanResponseDTO getRecentPlan(@RequestAttribute(name = "user") User user) throws Exception{
        return planService.findRecentPlan(user.getUsername());
    }

    @PostMapping("/plan-test")
    public ResponseEntity<String> test(@RequestAttribute(name = "user") User user) {
        return ResponseEntity.ok("Test successful");
    }
}
