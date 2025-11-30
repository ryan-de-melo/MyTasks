package com.melo.backend.infrastructure.dto.dbprojections;

public record UserTaskDTO(
    String userEmail,
    String taskTitle
) {}
