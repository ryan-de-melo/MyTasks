package com.melo.backend.dto.task;

import java.time.Instant;

import com.melo.backend.entity.enums.TaskPriority;
import com.melo.backend.entity.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskCreateDTO(
        @NotBlank
    @Size(min = 5, max = 255)
    String title,

    @NotBlank
    String description,

    @NotNull
    TaskPriority priority,     // Is HIGH, MEDIUM, LOW

    @NotNull
    TaskStatus status,         // Is DO, DOING, DONE

    Instant deadline
) {}
