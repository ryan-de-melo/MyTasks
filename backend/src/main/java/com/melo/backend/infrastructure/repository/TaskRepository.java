package com.melo.backend.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melo.backend.infrastructure.dto.dbprojections.UserTaskDTO;
import com.melo.backend.infrastructure.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByTitle(String title);

    @Query("""
            SELECT new com.melo.backend.infrastructure.dto.task.UserTaskDTO(
                u.name,
                t.title
            ) 
            FROM Task t
            JOIN t.user u
        """)
    List<UserTaskDTO> findAllUserAndTask();
}
