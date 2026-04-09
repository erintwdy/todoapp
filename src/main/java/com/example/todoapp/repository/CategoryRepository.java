package com.example.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}