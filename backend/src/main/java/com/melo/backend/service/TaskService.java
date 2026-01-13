package com.melo.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melo.backend.dto.task.TaskAddDTO;
import com.melo.backend.dto.task.TaskCreateDTO;
import com.melo.backend.dto.task.TaskResponseDTO;
import com.melo.backend.dto.task.TaskUpdateDTO;
import com.melo.backend.entity.Task;
import com.melo.backend.entity.User;
import com.melo.backend.mappers.TaskMapper;
import com.melo.backend.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticatedUserService authUserService;

    /**
     * 
     * @param dto
     * @return
     */
    @Deprecated
    public TaskResponseDTO addTaskUnsafe(TaskAddDTO dto) {
        Task toAdd = new Task();

        toAdd.setTitle(dto.title());
        toAdd.setDescription(dto.description());
        toAdd.setPriority(dto.priority());
        toAdd.setStatus(dto.status());
        toAdd.setUser(userService.getUserEntityById(dto.userId()));
        toAdd.setDeadline(dto.deadline());

        return TaskMapper.toResponse(repository.save(toAdd));
    }

    
    public TaskResponseDTO addTask(TaskCreateDTO dto) {
        User usr = authUserService.get();
        Task toAdd = Task.builder()
                            .title(dto.title())
                            .description(dto.description())
                            .priority(dto.priority())
                            .status(dto.status())
                            .user(usr)
                            .deadline(dto.deadline())
                            .build();
        
        return TaskMapper.toResponse(repository.save(toAdd));
    }

    /**
     * 
     * @param id
     * @return
     */
    @Deprecated
    public TaskResponseDTO deleteByIdUnsafe(Long id) {
        Task deleted = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found"));
        repository.deleteById(id);
        return TaskMapper.toResponse(deleted);
    }

    public TaskResponseDTO deleteById(Long id) {
        User usr = authUserService.get();
        Task deleted = repository.findByIdAndUser(id, usr).orElseThrow(
            () -> new RuntimeException("Not found"));

        repository.delete(deleted);
        return TaskMapper.toResponse(deleted);
    }

    /**
     * 
     * @param id
     * @return
    */
   @Deprecated
    public TaskResponseDTO getById(Long id) {
        return TaskMapper.toResponse(repository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found")));
    }

    /**
     * 
     * @param id
     * @param dto
     * @return
     */
    @Deprecated
    @Transactional
    public TaskResponseDTO partialUpdateById(Long id, TaskUpdateDTO dto) {
        Task toUpdate = repository.findById(id).orElseThrow(
                () -> new RuntimeException("ERROR"));

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

    /**
     * 
     * @return
     */
    @Deprecated
    public List<TaskResponseDTO> getAllUnsafe() {
        return repository.findAll().stream().map(TaskResponseDTO::new).toList();
    }

    public List<TaskResponseDTO> getAll() {
        User usr = authUserService.get();
        
        return repository.findByUser(usr)
            .stream()
            .map(TaskResponseDTO::new)
            .toList();
    }
}
