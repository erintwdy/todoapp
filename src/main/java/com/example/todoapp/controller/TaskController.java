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

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Data berhasil diambil", taskService.getAll())
        );
    }

    // 🔥 GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Data berhasil diambil", taskService.getById(id))
        );
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> create(@Valid @RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Task berhasil dibuat", taskService.create(request)));
    }

    // 🔥 UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Task berhasil diupdate", taskService.update(id, request))
        );
    }

    // 🔥 DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {

        taskService.delete(id);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Task berhasil dihapus", null)
        );
    }
}