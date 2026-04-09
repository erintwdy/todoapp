package com.example.todoapp.service;

import com.example.todoapp.model.dto.request.TaskRequest;
import com.example.todoapp.model.dto.response.TaskResponse;
import com.example.todoapp.model.entity.*;
import com.example.todoapp.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository,
                       CategoryRepository categoryRepository,
                       UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    // ✅ GET (JOIN)
    public List<TaskResponse> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(this::mapToResponse).toList();
    }

    // ✅ POST
    public TaskResponse create(TaskRequest request) {

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setPriority(Priority.valueOf(request.getPriority()));
        task.setStatus(Status.valueOf(request.getStatus()));

        // user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
        task.setUser(user);

        // category
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category tidak ditemukan"));
        task.setCategory(category);

        Task saved = taskRepository.save(task);

        return mapToResponse(saved);
    }

    // 🔁 mapping JOIN
    private TaskResponse mapToResponse(Task task) {
        TaskResponse res = new TaskResponse();

        res.setId(task.getId());
        res.setTitle(task.getTitle());
        res.setDescription(task.getDescription());
        res.setPriority(task.getPriority().name());
        res.setStatus(task.getStatus().name());
        res.setDueDate(task.getDueDate());

        if (task.getUser() != null) {
            res.setUserName(task.getUser().getName());
            res.setUserEmail(task.getUser().getEmail());
        }

        if (task.getCategory() != null) {
            res.setCategoryName(task.getCategory().getCategoryName());
        }

        return res;
    }
}