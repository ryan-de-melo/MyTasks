package com.melo.backend.infrastructure.dto;

import java.time.Instant;

import com.melo.backend.infrastructure.enums.TaskPriority;
import com.melo.backend.infrastructure.enums.TaskStatus;
import com.melo.backend.infrastructure.model.User;

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
    TaskPriority priority,

    @NotNull
    TaskStatus status,

    @NotNull
    User user,

    Instant deadline
) {}
