package com.melo.backend.repository.dbprojections;

import java.io.Serializable;

public record UserTaskDTO(
    String userEmail,
    String taskTitle
) implements Serializable {}
