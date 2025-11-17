package com.melo.backend.dto;

import com.melo.backend.infrastructure.enums.TaskPriority;
import com.melo.backend.infrastructure.enums.TaskStatus;

public record TaskAddDTO(
    Long id,
    String title,
    String description,
    TaskPriority priority,
    TaskStatus status,
    Long fk_user_id
) {}
