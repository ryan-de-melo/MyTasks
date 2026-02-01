package com.melo.backend.dto.task;

import java.io.Serializable;
import java.time.Instant;

import com.melo.backend.dto.user.UserResponseDTO;
import com.melo.backend.entity.Task;
import com.melo.backend.entity.enums.TaskPriority;
import com.melo.backend.entity.enums.TaskStatus;
import com.melo.backend.mappers.UserMapper;

public record TaskResponseDTO(
    Long id,
    String title,
    String description,
    TaskPriority priority,
    TaskStatus status,
    Instant createdAt,
    Instant updatedAt,
    Instant deadline,
    UserResponseDTO user
) implements Serializable {
    public TaskResponseDTO(Task task) {
        this(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getPriority(),
            task.getStatus(),
            task.getCreatedAt(),
            task.getUpdatedAt(),
            task.getDeadline(),
            UserMapper.toResponse(task.getUser())
        );
    }
}
