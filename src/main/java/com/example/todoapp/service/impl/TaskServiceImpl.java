package com.example.todoapp.service.impl;

import com.example.todoapp.model.dto.request.TaskRequestDTO;
import com.example.todoapp.model.dto.response.TaskResponseDTO;
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
    public List<TaskResponseDTO> getAll() {
        return taskRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public TaskResponseDTO getById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
        return mapToResponse(task);
    }

    @Override
    public TaskResponseDTO create(TaskRequestDTO request) {

        Task task = new Task();
        setTaskFields(task, request);

        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }

    @Override
    public TaskResponseDTO update(Long id, TaskRequestDTO request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));

        setTaskFields(task, request);

        Task updated = taskRepository.save(task);
        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));

        taskRepository.delete(task);
    }

    private void setTaskFields(Task task, TaskRequestDTO request) {

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());

        try {
            task.setPriority(Priority.valueOf(request.getPriority().toUpperCase()));
            task.setStatus(Status.valueOf(request.getStatus().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid priority or status");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        task.setUser(user);

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));
        task.setCategory(category);
    }

    private TaskResponseDTO mapToResponse(Task task) {
        TaskResponseDTO res = new TaskResponseDTO();

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