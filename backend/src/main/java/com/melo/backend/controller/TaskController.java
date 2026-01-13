package com.melo.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melo.backend.dto.task.TaskAddDTO;
import com.melo.backend.dto.task.TaskResponseDTO;
import com.melo.backend.dto.task.TaskUpdateDTO;
import com.melo.backend.service.TaskService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> addTask(@RequestBody TaskAddDTO dto) {
        return ResponseEntity.ok(service.addTask(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> partialUpdateTaskById(@PathVariable Long id, @RequestBody TaskUpdateDTO dto) {
        return ResponseEntity.ok(service.partialUpdateById(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        return ResponseEntity.ok(service.getAll());
    }

}
