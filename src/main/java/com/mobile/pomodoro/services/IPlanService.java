package com.mobile.pomodoro.services;

import com.mobile.pomodoro.CustomException.UserNotFoundException;
import com.mobile.pomodoro.dto.request.PlanRequestDTO;
import com.mobile.pomodoro.dto.response.MessageResponseDTO;
import com.mobile.pomodoro.dto.response.PlanResponeseDTO.PlanResponeseDTO;
import com.mobile.pomodoro.dto.response.PlanResponseDTO.PlanResponseDTO;
import com.mobile.pomodoro.dto.response.TaskToEditResponseDTO.TaskToEditResponseDTO;
import com.mobile.pomodoro.entities.User;

public interface IPlanService extends IInitializerData{

    // Define the methods for plan-related operations
    public PlanResponseDTO findPlan(String username) throws UserNotFoundException;
    MessageResponseDTO createPlan(PlanRequestDTO requestDTO, User user);
    PlanResponseDTO findRecentPlan(String username) throws UserNotFoundException;
}
