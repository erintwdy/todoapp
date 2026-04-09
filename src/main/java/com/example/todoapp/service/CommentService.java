package com.example.todoapp.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todoapp.model.entity.Comment;
import com.example.todoapp.model.entity.Task;
import com.example.todoapp.repository.CommentRepository;
import com.example.todoapp.repository.TaskRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    public CommentService(CommentRepository commentRepository,
                          TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Comment create(Long taskId, Comment comment) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task tidak ditemukan"));

        comment.setTask(task);
        return commentRepository.save(comment);
    }
}
