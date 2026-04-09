package com.example.todoapp.model.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "tasks")
@JsonPropertyOrder({ "id", "categoryName" })
@Entity
@Table(name = "task_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name") 
    private String categoryName;

    @Column(name = "description") 
    private String description;

    @JsonIgnore 
    @OneToMany(mappedBy = "category")
    private List<Task> tasks;
}