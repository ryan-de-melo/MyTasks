package com.melo.backend.repository.dbprojections;

import java.time.Instant;

import com.melo.backend.entity.Task;
import com.melo.backend.entity.enums.TaskPriority;
import com.melo.backend.entity.enums.TaskStatus;

public record TaskDTO(
    Long id,
    String title,
    String description,
    Instant createdAt,
    Instant updatedAt,
    Instant deadline,
    TaskStatus status,
    TaskPriority priority
) {
    public TaskDTO(Task task) {
        this(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getCreatedAt(),
            task.getUpdatedAt(),
            task.getDeadline(),
            task.getStatus(),
            task.getPriority()
        );
    }
}
