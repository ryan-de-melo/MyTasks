package com.melo.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.melo.backend.entity.Task;
import com.melo.backend.entity.User;
import com.melo.backend.repository.dbprojections.TaskDTO;
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

    List<Task> findByUser(User user);

    @Query(
        """
            SELECT new com.melo.backend.repository.dbprojection.TaskDTO(
                t.title,
                t.description,
                t.createdAt,
                t.updatedAt,
                t.deadline,
                t.status,
                t.priority
            )
            FROM
                Task t
            WHERE
                t.user.id = :userId
        """
    )
    List<TaskDTO> searchByUser(@Param("userId") Long userId);


    @Query(
        """
            SELECT new com.melo.backend.repository.dbprojection.TaskDTO(
                t.title,
                t.description,
                t.createdAt,
                t.updatedAt,
                t.deadline,
                t.status,
                t.priority
            )
            FROM
                Task t
        """
    )
    List<TaskDTO> findAllTasks();

    @Query(
        """
            SELECT new com.melo.backend.repository.dbprojection.TaskDTO(
                t.title,
                t.description,
                t.createdAt,
                t.updatedAt,
                t.deadline,
                t.status,
                t.priority
            )
            FROM
                Task t
            WHERE
                t.id = :taskId
                AND t.user.id = :userId
        """
    )
    Optional<TaskDTO> findByIdAndUserId(@Param("taskId") Long taskId, @Param("userId") Long userId);
}
