package com.example.todoapp.controller;

import com.example.todoapp.model.dto.request.TaskRequest;
import com.example.todoapp.model.dto.response.TaskResponse;
import com.example.todoapp.model.dto.response.ApiResponse;
import com.example.todoapp.service.interfaces.TaskService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ✅ GET
    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getAll() {

        List<TaskResponse> tasks = taskService.getAll();

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Data berhasil diambil", tasks)
        );
    }

    // ✅ POST
    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> create(@Valid @RequestBody TaskRequest request) {

        TaskResponse task = taskService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Task berhasil dibuat", task));
    }
}