package com.melo.backend.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melo.backend.dto.TaskAddDTO;
import com.melo.backend.infrastructure.model.Task;
import com.melo.backend.infrastructure.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(TaskAddDTO dto) {
        Task toAdd = new Task();

        toAdd.setId(dto.id());
        toAdd.setTitle(dto.title());
        toAdd.setDescription(dto.description());
        toAdd.setPriority(dto.priority());
        toAdd.setStatus(dto.status());
        toAdd.setFk_user_id(dto.fk_user_id());

        return taskRepository.save(toAdd);
    }
}
