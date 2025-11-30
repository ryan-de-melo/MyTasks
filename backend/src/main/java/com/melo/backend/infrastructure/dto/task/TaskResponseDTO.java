package com.melo.backend.infrastructure.dto.task;

import java.time.Instant;

import com.melo.backend.infrastructure.dto.mappers.UserMapper;
import com.melo.backend.infrastructure.dto.user.UserResponseDTO;
import com.melo.backend.infrastructure.enums.TaskPriority;
import com.melo.backend.infrastructure.enums.TaskStatus;
import com.melo.backend.infrastructure.model.Task;

public record TaskResponseDTO(
    String title,
    String description,
    TaskPriority priority,
    TaskStatus status,
    Instant createdAt,
    Instant updatedAt,
    Instant deadline,
    UserResponseDTO user
) {
    public TaskResponseDTO(Task task) {
        this(task.getTitle(),
            task.getDescription(),
            task.getPriority(),
            task.getStatus(),
            task.getCreatedAt(),
            task.getUpdatedAt(),
            task.getDeadline(),
            UserMapper.toResponse(task.getUser()));
    }
}
