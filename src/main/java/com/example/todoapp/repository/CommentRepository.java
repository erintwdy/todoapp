package com.example.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}