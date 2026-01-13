package com.melo.backend.repository.dbprojections;

public record UserTaskDTO(
    String userEmail,
    String taskTitle
) {}
