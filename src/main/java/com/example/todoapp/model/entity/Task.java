package com.example.todoapp.model.entity;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"comments", "category", "user"})
@JsonPropertyOrder({
    "id",
    "title",
    "description",
    "dueDate",
    "priority",
    "status",
    "category",
    "user"
})
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "task")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}