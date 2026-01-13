package com.melo.backend.dto.task;

import com.melo.backend.entity.enums.TaskPriority;
import com.melo.backend.entity.enums.TaskStatus;

public record TaskUpdateDTO(
    String title,
    String description,     
    TaskPriority priority,   // Is HIGH, MEDIUM, LOW
    TaskStatus status        // Is DO, DOING, DONE
) {}
