package com.melo.backend.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.melo.backend.dto.task.TaskCreateDTO;
import com.melo.backend.dto.task.TaskResponseDTO;
import com.melo.backend.entity.Task;
import com.melo.backend.entity.User;
import com.melo.backend.entity.enums.TaskPriority;
import com.melo.backend.entity.enums.TaskStatus;
import com.melo.backend.repository.TaskRepository;
import com.melo.backend.service.AuthenticationService;
import com.melo.backend.service.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @Mock
    private AuthenticationService authService;

    @InjectMocks
    private TaskService service;

    private User userDefault;

    @BeforeAll
    public void setUp() {
        userDefault = User.builder()
                .id(Long.valueOf(0))
                .email("test@email.com")
                .name("test")
                .password("testpassword")
                .build();
    }


    @Test
    void testCreateTask() {

        // modelo 
        // definições
        // ação de teste
        // asserts

        User user = User.builder()
                .id(Long.valueOf(0))
                .email("test@email.com")
                .name("test")
                .password("testpassword")
                .build();

        TaskCreateDTO dto = new TaskCreateDTO(
            "id 1", 
            "teste", 
            TaskPriority.HIGH, 
            TaskStatus.DOING, 
            LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-03:00"))
        );

        Task savedTask = Task.builder()
                .id(1L)
                .title(dto.title())
                .description(dto.description())
                .priority(dto.priority())
                .status(dto.status())
                .deadline(dto.deadline())
                .user(user)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        when(authService.getCurrentUser()).thenReturn(user);
        when(repository.save(any(Task.class))).thenReturn(savedTask);

        TaskResponseDTO response = service.addTask(dto);

        assertNotNull(response);
        assertEquals(dto.title(), response.title());
    }

    @Test
    void testDeleteTask() {
        Task savedTask = Task.builder()
                .id(1L)
                .title("Teste")
                .description("testando")
                .priority(TaskPriority.HIGH)
                .status(TaskStatus.DOING)
                .deadline(LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-03:00")))
                .user(userDefault)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        
    }

}
