package com.mobile.pomodoro.mapper.response;

import com.mobile.pomodoro.dto.response.PlanResponseDTO.PlanResponseDTO;
import com.mobile.pomodoro.entities.Plan;
import com.mobile.pomodoro.entities.PlanTask;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-15T03:05:16+0000",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.14 (Ubuntu)"
)
@Component
public class PlanResponseDTOMapperImpl extends PlanResponseDTOMapper {

    @Override
    public PlanResponseDTO mapToDTO(Plan plan) {
        if ( plan == null ) {
            return null;
        }

        PlanResponseDTO.PlanResponseDTOBuilder planResponseDTO = PlanResponseDTO.builder();

        planResponseDTO.planId( plan.getPlanId() );
        planResponseDTO.planTitle( plan.getTitle() );

        return planResponseDTO.build();
    }

    @Override
    public PlanResponseDTO.TaskDTO mapToDTO(PlanTask planTask) {
        if ( planTask == null ) {
            return null;
        }

        PlanResponseDTO.TaskDTO.TaskDTOBuilder taskDTO = PlanResponseDTO.TaskDTO.builder();

        taskDTO.task_name( planTask.getTask_name() );
        taskDTO.duration( planTask.getDuration() );
        taskDTO.task_order( planTask.getTask_order() );

        return taskDTO.build();
    }
}
