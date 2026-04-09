package com.example.todoapp.controller;

import com.example.todoapp.model.entity.Comment;
import com.example.todoapp.model.entity.Task;
import com.example.todoapp.repository.CommentRepository;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @PostMapping
    public Comment create(@RequestParam Long taskId, @RequestBody Comment comment) {
        return commentService.create(taskId, comment);
    }
}