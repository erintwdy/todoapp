package com.example.todoapp.service.impl;

import com.example.todoapp.model.dto.request.TaskRequest;
import com.example.todoapp.model.dto.response.TaskResponse;
import com.example.todoapp.model.entity.*;
import com.example.todoapp.repository.*;
import com.example.todoapp.service.interfaces.TaskService;
import com.example.todoapp.exception.NotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository,
                           CategoryRepository categoryRepository,
                           UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TaskResponse> getAll() {
        return taskRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public TaskResponse getById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task tidak ditemukan"));
        return mapToResponse(task);
    }

    @Override
    public TaskResponse create(TaskRequest request) {

        Task task = new Task();
        setTaskFields(task, request);

        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }

    @Override
    public TaskResponse update(Long id, TaskRequest request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task tidak ditemukan"));

        setTaskFields(task, request);

        Task updated = taskRepository.save(task);
        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task tidak ditemukan"));

        taskRepository.delete(task);
    }

    // 🔥 HELPER BIAR TIDAK DUPLIKASI
    private void setTaskFields(Task task, TaskRequest request) {

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());

        try {
            task.setPriority(Priority.valueOf(request.getPriority().toUpperCase()));
            task.setStatus(Status.valueOf(request.getStatus().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Priority atau Status tidak valid");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User tidak ditemukan"));
        task.setUser(user);

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category tidak ditemukan"));
        task.setCategory(category);
    }

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