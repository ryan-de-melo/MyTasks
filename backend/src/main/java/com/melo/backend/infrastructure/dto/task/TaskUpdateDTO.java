package com.melo.backend.infrastructure.dto.task;

import com.melo.backend.infrastructure.enums.TaskPriority;
import com.melo.backend.infrastructure.enums.TaskStatus;

public record TaskUpdateDTO(
    String title,
    String description,     
    TaskPriority priority,   // Is HIGH, MEDIUM, LOW
    TaskStatus status        // Is DO, DOING, DONE
) {}
