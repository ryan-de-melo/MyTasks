package com.melo.backend.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melo.backend.infrastructure.dto.mappers.TaskMapper;
import com.melo.backend.infrastructure.dto.task.TaskAddDTO;
import com.melo.backend.infrastructure.dto.task.TaskResponseDTO;
import com.melo.backend.infrastructure.dto.task.TaskUpdateDTO;
import com.melo.backend.infrastructure.model.Task;
import com.melo.backend.infrastructure.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private UserService userService;

    public TaskResponseDTO addTask(TaskAddDTO dto) {
        Task toAdd = new Task();

        toAdd.setTitle(dto.title());
        toAdd.setDescription(dto.description());
        toAdd.setPriority(dto.priority());
        toAdd.setStatus(dto.status());
        toAdd.setUser(userService.getUserEntityById(dto.userId()));
        toAdd.setDeadline(dto.deadline());

        return TaskMapper.toResponse(repository.save(toAdd));
    }

    public TaskResponseDTO deleteById(Long id) {
        Task deleted = repository.findById(id).orElseThrow(
            () -> new RuntimeException("Task not found")
        );
        repository.deleteById(id);
        return TaskMapper.toResponse(deleted);
    }

    public TaskResponseDTO getById(Long id) {
        return TaskMapper.toResponse(repository.findById(id).orElseThrow(
            () -> new RuntimeException("Task not found")
        ));
    }

    @Transactional
    public TaskResponseDTO partialUpdateById(Long id, TaskUpdateDTO dto) {
        Task toUpdate = repository.findById(id).orElseThrow(
            () -> new RuntimeException("ERROR")
        );

        if (dto.title() != null && !dto.title().isEmpty()) {
            toUpdate.setTitle(dto.title());
        }
        if (dto.description() != null && !dto.description().isEmpty()) {
            toUpdate.setDescription(dto.description());
        }
        if (dto.priority() != null) {
            toUpdate.setPriority(dto.priority());
        }
        if (dto.status() != null) {
            toUpdate.setStatus(dto.status());
        }

        return TaskMapper.toResponse(toUpdate);
    }

    public List<TaskResponseDTO> getAll() {
        return repository.findAll().stream().map(TaskResponseDTO :: new).toList();
    }
}
