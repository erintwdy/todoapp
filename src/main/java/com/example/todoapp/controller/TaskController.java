package com.example.todoapp.controller;

import com.example.todoapp.model.dto.request.TaskRequestDTO;
import com.example.todoapp.model.dto.response.TaskResponseDTO;
import com.example.todoapp.model.dto.response.ApiResponse;
import com.example.todoapp.service.interfaces.TaskService;

import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponseDTO>>> getAll() {

        List<TaskResponseDTO> data = taskService.getAll();

        return ResponseEntity.ok(
                new ApiResponse<>("success", "Data retrieved successfully", data)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDTO>> getById(@PathVariable Long id) {

        TaskResponseDTO data = taskService.getById(id);

        return ResponseEntity.ok(
                new ApiResponse<>("success", "Data retrieved successfully", data)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponseDTO>> create(@Valid @RequestBody TaskRequestDTO request) {

        TaskResponseDTO data = taskService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", "Task created successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO request) {

        TaskResponseDTO data = taskService.update(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>("success", "Task updated successfully", data)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {

        taskService.delete(id);

        return ResponseEntity.ok(
                new ApiResponse<>("success", "Task deleted successfully", null)
        );
    }
}