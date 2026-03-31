package com.example.todoapp.controller;

import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.Category;
import com.example.todoapp.entity.User;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.CategoryRepository;
import com.example.todoapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {

        if (task.getCategory() != null) {
            Long categoryId = task.getCategory().getId();
            Category category = categoryRepository.findById(categoryId).orElse(null);
            task.setCategory(category);
        }

        if (task.getUser() != null) {
            Long userId = task.getUser().getId();
            User user = userRepository.findById(userId).orElse(null);
            task.setUser(user);
        }

        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {

        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();

            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setPriority(updatedTask.getPriority());
            task.setStatus(updatedTask.getStatus());

            if (updatedTask.getCategory() != null) {
                Long categoryId = updatedTask.getCategory().getId();
                Category category = categoryRepository.findById(categoryId).orElse(null);
                task.setCategory(category);
            }

            if (updatedTask.getUser() != null) {
                Long userId = updatedTask.getUser().getId();
                User user = userRepository.findById(userId).orElse(null);
                task.setUser(user);
            }

            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "Task berhasil dihapus";
    }
}