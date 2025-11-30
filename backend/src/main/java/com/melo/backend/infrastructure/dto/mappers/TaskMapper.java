package com.melo.backend.infrastructure.dto.mappers;

import com.melo.backend.infrastructure.dto.task.TaskAddDTO;
import com.melo.backend.infrastructure.dto.task.TaskResponseDTO;
import com.melo.backend.infrastructure.model.Task;

public class TaskMapper {

    public static Task toEntity(TaskAddDTO dto) {
        return Task.builder()
                    .title(dto.title())
                    .description(dto.description())
                    .priority(dto.priority())
                    .status(dto.status())
                    .deadline(dto.deadline())
                    .build();
    }

    public static TaskResponseDTO toResponse(Task task) {
        return new TaskResponseDTO(task.getTitle(), 
                                    task.getDescription(), 
                                    task.getPriority(), 
                                    task.getStatus(), 
                                    task.getCreatedAt(), 
                                    task.getUpdatedAt(), 
                                    task.getDeadline(),
                                    UserMapper.toResponse(task.getUser()));
    }
}
