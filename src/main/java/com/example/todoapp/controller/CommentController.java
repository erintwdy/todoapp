package com.example.todoapp.controller;

import com.example.todoapp.entity.Comment;
import com.example.todoapp.entity.Task;
import com.example.todoapp.repository.CommentRepository;
import com.example.todoapp.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @PostMapping
    public Comment create(@RequestParam Long taskId, @RequestBody Comment comment) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        comment.setTask(task);
        return commentRepository.save(comment);
    }
}