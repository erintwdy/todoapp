package com.example.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}