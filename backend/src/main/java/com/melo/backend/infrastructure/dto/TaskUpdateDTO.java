package com.melo.backend.infrastructure.dto;

import com.melo.backend.infrastructure.enums.TaskPriority;
import com.melo.backend.infrastructure.enums.TaskStatus;

public record TaskUpdateDTO(
    String title,
    String description,
    TaskPriority priority,
    TaskStatus status
) {}
