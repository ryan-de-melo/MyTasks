package com.melo.backend.infrastructure.dto.task;

import java.time.Instant;

import com.melo.backend.infrastructure.enums.TaskPriority;
import com.melo.backend.infrastructure.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskAddDTO(
    @NotBlank
    @Size(min = 5, max = 255)
    String title,

    @NotBlank
    String description,

    @NotNull
    TaskPriority priority,     // Is HIGH, MEDIUM, LOW

    @NotNull
    TaskStatus status,         // Is DO, DOING, DONE

    @NotNull
    Long userId,

    Instant deadline
) {}
