package com.melo.backend.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melo.backend.infrastructure.dto.TaskAddDTO;
import com.melo.backend.infrastructure.dto.TaskUpdateDTO;
import com.melo.backend.infrastructure.model.Task;
import com.melo.backend.infrastructure.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public Task addTask(TaskAddDTO dto) {
        Task toAdd = new Task();

        toAdd.setTitle(dto.title());
        toAdd.setDescription(dto.description());
        toAdd.setPriority(dto.priority());
        toAdd.setStatus(dto.status());
        toAdd.setUser(dto.user());
        toAdd.setDeadline(dto.deadline());

        return repository.save(toAdd);
    }

    public Task deleteById(Long id) {
        Task deleted = repository.findById(id).orElseThrow(
            () -> new RuntimeException("Task not found")
        );
        repository.deleteById(id);
        return deleted;
    }

    public Task getById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RuntimeException("Task not found")
        );
    }

    public Task updateById(Long id, TaskUpdateDTO dto) {
        Task toUpdate = repository.findById(id).orElseThrow(
            () -> new RuntimeException("ERROR")
        );

        if (dto.title() != null && dto.title().isEmpty()) {
            toUpdate.setTitle(dto.title());
        }
        if (dto.description() != null && dto.description().isEmpty()) {
            toUpdate.setDescription(dto.description());
        }
        if (dto.priority() != null) {
            toUpdate.setPriority(dto.priority());
        }
        if (dto.status() != null) {
            toUpdate.setStatus(dto.status());
        }

        return toUpdate;
    }

    public List<Task> getAll() {
        return repository.findAll();
    }
}
