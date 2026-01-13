package com.melo.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melo.backend.entity.Task;
import com.melo.backend.entity.User;
import com.melo.backend.repository.dbprojections.UserTaskDTO;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByTitle(String title);

    @Query("""
                SELECT new com.melo.backend.repository.dbprojections.UserTaskDTO(
                    u.name,
                    t.title
                )
                FROM Task t
                JOIN t.user u
            """)
    List<UserTaskDTO> findAllUserAndTask();

    Optional<Task> findByIdAndUser(Long id, User user);
}
