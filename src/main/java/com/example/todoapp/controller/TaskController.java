package com.example.todoapp.controller;

import com.example.todoapp.model.dto.request.TaskRequest;
import com.example.todoapp.model.dto.response.TaskResponse;
import com.example.todoapp.service.TaskService;

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

    @GetMapping
    //Get all tasks
    public ResponseEntity<List<TaskResponse>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.create(request));
    }
}